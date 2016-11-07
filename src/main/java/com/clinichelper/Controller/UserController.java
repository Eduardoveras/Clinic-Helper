package com.clinichelper.Controller;

import com.clinichelper.Entity.User;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

/**
 * Created by Eduardo veras on 28-Oct-16.
 */
@Controller
public class UserController {

    // Services
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @Autowired
    private ToolKitService TKS;

    // TODO: this can only be used by SUPERADMIN
    @GetMapping("/admin/users")
    public ModelAndView fetchAllPatientsView(Model model){
        model.addAttribute("todoList", TKS.InitializeTodoList("CH-PLATINUM-JASC"));


        //model.addAttribute("userList", DQS.findAllAllRegisteredUsersForClinic("CH-PLATINUM-JASC"));
        //model.addAttribute("amount", DQS.findAllAllRegisteredUsersForClinic("CH-PLATINUM-JASC").size());
       // model.addAttribute("userList", DQS.findAllAllRegisteredUsersForClinic(clinicId));
        //model.addAttribute("amount", DQS.findAllAllRegisteredUsersForClinic(clinicId).size());

        return new ModelAndView("/users/allUsers");
    }

    @RequestMapping("/login")
    public ModelAndView fetchLoginView(){
        return new ModelAndView("/users/login_register");
    }

    @RequestMapping("/logout")
    public ModelAndView logOut(){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        DQS.logOut();
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/user/{id}")
    public ModelAndView fetchUserProfile(Model model,@PathVariable(value="id") String userId){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");
        User u = DQS.findUserInformation(userId);
        model.addAttribute("todoList", TKS.InitializeTodoList(u.getClinic().getClinicId()));
        model.addAttribute("user", u);
        return new ModelAndView("users/userProfile");
    }

    @PostMapping("/uploadProfilePhoto")
    public String uploadProfilePicture(@RequestParam() String email, @RequestParam("clinic") String clinicId, @RequestParam("photo") MultipartFile file){

        if (!DQS.isUserLoggedIn() && DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/login";

        User user = DQS.findRegisteredUserAccount(email,clinicId);

        try {
            DEAMS.editUserPhoto(email, clinicId, processImageFile(file.getBytes()));
            return "redirect:/user/" + user.getUserId();
        } catch (PersistenceException exp){
            //
        } catch (IllegalArgumentException exp) {
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/user/" + user.getUserId(); // TODO: add error exception logic
    }

    @PostMapping("/userLogin")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password){
        
        if (DQS.validateUserAccountCredentials(email, password))
        {
            User u = DQS.findRegisteredUserAccount(email,password);
            DQS.setSessionAttr("user",u);
            return "redirect:/"; // TODO: filter which user is login in to redirect them to the correct url
        }
        else
            return "redirect:/login"; // TODO: Implement error exception or message to login
    }

    @PostMapping("/editMyPassword")
    public String editUserPassword(@RequestParam("email") String email, @RequestParam("clinic") String clinicId, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.validateUserAccountCredentials(email.toLowerCase(), password)){

            User user = DQS.findRegisteredUserAccount(email.toLowerCase(),clinicId);

            try {
                DEAMS.editUserAccountCredentials(user.getEmail(), user.getClinic().getClinicId(), newPassword, user.getRole());
                return "redirect:/user/" + user.getUserId();
            } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
                //
            } catch (Exception exp){
                //
            }
            return "redirect:/user/" + user.getUserId(); // TODO: Implement error exception or message to edit password
        }
        else
            return "redirect:/"; // The impossible happened! Ore the credential were wrong
    }

    //Auxiliary Functions
    private Byte[] processImageFile(byte[] buffer) {
        Byte[] bytes = new Byte[buffer.length];
        int i = 0;

        for (byte b :
                buffer)
            bytes[i++] = b; // Autoboxing

        return bytes;
    }
}
