package com.clinichelper.Controller;

import com.clinichelper.Entity.User;
import com.clinichelper.Service.CRUD.DataCreationService;
import com.clinichelper.Service.CRUD.DataDeleteService;
import com.clinichelper.Service.CRUD.DataUpdateService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.Gender;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Eduardo veras on 02-Oct-16.
 */

@Controller
public class TeamController {

    // Services
    // CRUD
    @Autowired
    private DataCreationService DCS;
    @Autowired
    private DataUpdateService DUS;
    @Autowired
    private DataDeleteService DDS;
    @Autowired
    private DataQueryService DQS;
    //
    @Autowired
    private ToolKitService TKS;

    // Gets
    @GetMapping("/users")
    public ModelAndView fetchAllPatientsView(Model model){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return new ModelAndView("redirect:/");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

        model.addAttribute("todoList", TKS.InitializeTodoList(DQS.getCurrentLoggedUser().getUserId()));
        model.addAttribute("userList", DQS.findAllAllRegisteredUsersForClinic(clinicId));
        model.addAttribute("clinicId", DQS.findAllAllRegisteredUsersForClinic(clinicId).get(0).getClinic().getClinicPrefix());

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("/users/allUsers");
    }

    // Post
    @PostMapping("/newUser")
    public String newUser(@RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("dateOfBirth") String  birthDate, @RequestParam("gender") String gender, @RequestParam("password") String password, @RequestParam("role") String role){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        try{
            DCS.createNewUserAccount(DQS.getCurrentLoggedUser().getClinic().getClinicId(), email.toLowerCase(), firstName, lastName, new Date(new SimpleDateFormat("MM/dd/yyyy").parse(birthDate).getTime()), gender.toUpperCase().equals("F") ? Gender.F : Gender.M, password, role.toUpperCase().equals("M") ? Permission.MEDIC : Permission.ASSISTANT);
            return "redirect:/users";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/users"; // TODO: add error exception handler
    }

    @PostMapping("/deleteUser")
    public String deleteUser (@RequestParam("id") String userId){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        try {
            DDS.deleteRegisteredUserAccount(userId);
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect/team";
    }

    @PostMapping("/makUserAdmin") //Only accessed by the admin of the clinic account
    public String makeUserDamin(@RequestParam("id") String userId){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/";

        User user = DQS.findUserInformation(userId);

        try {
            DUS.editUserAccountCredentials(user.getEmail(), user.getClinic().getClinicId(), user.getPassword(), Permission.ADMIN);
            return "redirect:/team";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/team"; // TODO: Implement error exception or message to edit password
    }

}
