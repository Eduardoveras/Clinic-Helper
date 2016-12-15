package com.clinichelper.Controller;

import com.clinichelper.Entity.User;
import com.clinichelper.Service.CRUD.DataUpdateService;
import com.clinichelper.Service.CRUD.Reading.ClinicInformationService;
import com.clinichelper.Service.Security.SessionService;
import com.clinichelper.Service.Native.ToolKitService;
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

/**
 * Created by Eduardo veras on 28-Oct-16.
 */
@Controller
public class UserController {

    // Services
    // CRUD
    @Autowired
    private DataUpdateService DUS;
    @Autowired
    private ClinicInformationService CIS;
    // Security
    @Autowired
    private SessionService sessionService;
    //
    @Autowired
    private ToolKitService TKS;

    // Gets
    // TODO: this can only be used by SUPERADMIN
    @GetMapping("/admin/users")
    public ModelAndView fetchAllPatientsView(Model model){
        return new ModelAndView("/users/allUsers");
    }

    @RequestMapping("/login")
    public ModelAndView fetchLoginView(){
        return new ModelAndView("/users/login_register");
    }

    @RequestMapping("/logout")
    public ModelAndView logOut(){
        if (!sessionService.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        sessionService.logOut();
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/user/{id}")
    public ModelAndView fetchUserProfile(Model model,@PathVariable(value="id") String userId){
        if (!sessionService.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        User u = CIS.findUserInformation(userId);
        model.addAttribute("todoList", TKS.InitializeTodoList(u.getUserId()));
        model.addAttribute("user", u);

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("users/userProfile");
    }

    // Post
    @PostMapping("/uploadProfilePhoto")
    public String uploadProfilePicture(@RequestParam() String email, @RequestParam("clinic") String clinicId, @RequestParam("photo") MultipartFile file){
        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        User user = CIS.findRegisteredUserAccount(email,clinicId);

        try {
            DUS.editUserPhoto(email, clinicId, processImageFile(file.getBytes()));
            return "redirect:/user/" + user.getUserId();
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/user/" + user.getUserId(); // TODO: add error exception logic
    }

    @PostMapping("/userLogin")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password){
        
        if (CIS.validateUserAccountCredentials(email.toLowerCase(), password))
        {
            User u = CIS.findRegisteredUserAccount(email.toLowerCase(),password);
            sessionService.setSessionAttr("user",u);
            return "redirect:/"; // TODO: filter which user is login in to redirect them to the correct url
        }
        else
            return "redirect:/login"; // TODO: Implement error exception or message to login
    }

    @PostMapping("/editMyPassword")
    public String editUserPassword(@RequestParam("email") String email, @RequestParam("clinic") String clinicId, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword){

        if (!sessionService.isUserLoggedIn())
            return "redirect:/login";

        if (CIS.validateUserAccountCredentials(email.toLowerCase(), password)){

            User user = CIS.findRegisteredUserAccount(email.toLowerCase(),clinicId);

            try {
                DUS.editUserAccountCredentials(user.getEmail(), user.getClinic().getClinicId(), newPassword, user.getRole());
                return "redirect:/user/" + user.getUserId();
            } catch (Exception exp){
                exp.printStackTrace();
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
