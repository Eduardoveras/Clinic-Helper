package com.clinichelper.Controller;

import com.clinichelper.Entity.Staff;
import com.clinichelper.Entity.User;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;

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


    @RequestMapping("/login")
    public ModelAndView fetchLoginView(Model model){

        model.addAttribute("name", "THIS IS NOT NECESSARY");

        return new ModelAndView("/users/login_register");
    }



    @RequestMapping("/profile")
    public ModelAndView fetchUserProfile(Model model, @RequestParam("username") String username){

        model.addAttribute("user", DQS.findRegisteredUserAccount(username));

        return new ModelAndView("");
    }


    @PostMapping("/uploadProfilePhoto")
    public String uploadProfilePicture(@RequestParam() String jascId, @RequestParam("photo") MultipartFile file){

        Staff staff = DQS.findRegisteredStaff(jascId);

        try {
            staff.setStaffPhoto(processImageFile(file.getBytes()));

            DEAMS.editStaff(staff);
        } catch (PersistenceException exp){
            //
        } catch (IllegalArgumentException exp) {
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/profile?username=" + DQS.findRegisteredUserAccountOfRegisteredStaff(jascId).getUsername();
    }

    @PostMapping("/newUser")
    public String newUser(@RequestParam("username") String username, @RequestParam("staff") String staff, @RequestParam("password") String password){

        try{
            DEAMS.createNewUserAccount(username,staff,password,"user");
        } catch (PersistenceException exp){
            //
        } catch (IllegalArgumentException exp) {
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/";
    }

    @PostMapping("/userLogin")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password){

        if (DQS.validateUserAccountCredentials(username, password))
            return "redirect:/"; // TODO: filter which user is login in to redirect them to the correct url
        else
            return "redirect:/login"; // TODO: Implement error exception or message to login
    }


    @PostMapping("/deleteUser")
    public String deleteUser (@RequestParam("username") String username){

        try {
            DEAMS.deleteRegisterdUserAccount(username);
        } catch (PersistenceException exp){
            //
        } catch (IllegalArgumentException exp) {
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect/team";
    }

    @PostMapping("/editUserPassword")
    public String editUserPassword(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword){

        if (DQS.validateUserAccountCredentials(username.toLowerCase(), password)){

            User user = DQS.findRegisteredUserAccount(username.toLowerCase());

            try {
                DEAMS.editUserAccountCredentials(user.getUsername(), newPassword, user.getRole());
            } catch (PersistenceException exp){
                //
            } catch (IllegalArgumentException exp) {
                //
            } catch (NullPointerException exp) {
                //
            } catch (Exception exp){
                //
            }
            return "redirect:/team";
        }
        else
            return "redirect:/editUserPassword"; // TODO: Implement error exception or message to edit password
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
