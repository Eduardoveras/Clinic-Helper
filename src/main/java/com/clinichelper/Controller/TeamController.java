package com.clinichelper.Controller;

import com.clinichelper.Entity.User;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Tools.Gender;
import com.clinichelper.Tools.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Eduardo veras on 02-Oct-16.
 */

@Controller
public class TeamController {
    // Services
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;

    // Gets
    @RequestMapping("/team")
    public ModelAndView fetchStaffView(Model model, @RequestParam("clinic") String clinicId){

        model.addAttribute("staffList", DQS.findAllRegisteredContactsForClinic(clinicId));
        model.addAttribute("userList", DQS.findAllAllRegisteredUsersForClinic(clinicId));

        return new ModelAndView("");
    }

    @GetMapping("/users")
    public ModelAndView fetchAllPatientsView(Model model){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        model.addAttribute("userList", DQS.findAllAllRegisteredUsersForClinic("CH-PLATINUM-JASC"));
        model.addAttribute("clinicId", DQS.findAllAllRegisteredUsersForClinic("CH-PLATINUM-JASC").get(0).getClinic().getClinicPrefix());
        // model.addAttribute("userList", DQS.findAllAllRegisteredUsersForClinic(clinicId));
        //model.addAttribute("amount", DQS.findAllAllRegisteredUsersForClinic(clinicId).size());

        return new ModelAndView("/users/allUsers");
    }

    // Post
    @PostMapping("/newUser")
    public String newUser(/* @RequestParam("clinic") String clinicId,*/@RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("dateOfBirth") String  birthDate, @RequestParam("gender") String gender, @RequestParam("password") String password, @RequestParam("role") String role){

        try{
            DEAMS.createNewUserAccount("CH-PLATINUM-JASC", email, firstName, lastName, new Date(new SimpleDateFormat("MM/dd/yyyy").parse(birthDate).getTime()), gender.toUpperCase().equals("F") ? Gender.F : Gender.M, password, role.toUpperCase().equals("M") ? Permission.MEDIC : Permission.ASSISTANT);
            return "redirect:/users";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/users"; // TODO: add error exception handler
    }

    @PostMapping("/deleteUser")
    public String deleteUser (@RequestParam("id") String userId){

        try {
            DEAMS.deleteRegisteredUserAccount(userId);
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect/team";
    }

    @PostMapping("/makUserAdmin") //Only accessed by the admin of the clinic account
    public String makeUserDamin(@RequestParam("id") String userId){

        User user = DQS.findUserInformation(userId);

        try {
            DEAMS.editUserAccountCredentials(user.getEmail(), user.getClinic().getClinicId(), user.getPassword(), Permission.ADMIN);
            return "redirect:/team";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/team"; // TODO: Implement error exception or message to edit password
    }

}
