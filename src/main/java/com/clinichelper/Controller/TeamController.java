package com.clinichelper.Controller;

import com.clinichelper.Entity.Staff;
import com.clinichelper.Entity.User;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;

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
    public ModelAndView fetchStaffView(Model model){

        model.addAttribute("staffList", DQS.findAllRegisteredStaffs());
        model.addAttribute("userList", DQS.findAllRegisteredUserAccounts());

        return new ModelAndView("");
    }

    @RequestMapping("/login")
    public ModelAndView fetchLoginView(Model model){

        model.addAttribute("name", "THIS IS NOT NECESSARY");

        return new ModelAndView("login");
    }

    // Post
    @PostMapping("/newStaff")
    public String newStaff(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String mail, @RequestParam("clinicId") String clinicId){

        try{
            DEAMS.createNewStaffMember(firstName,lastName,mail,clinicId);
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

    @PostMapping("/deleteStaff")
    public String deleteStaff (@RequestParam("jascID") String jascId){

        try {

            User user = DQS.findRegisteredUserAccountOfRegisteredStaff(jascId);

            if (user != null)
                DEAMS.deleteRegisterdUserAccount(user.getUsername());

            DEAMS.deleteRegisteredStaff(jascId);
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

    @PostMapping("/editStaff")
    public String editStaff(@RequestParam("jascId") String jascId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("mail") String mail, @RequestParam("clinicId") String clinicId){

        Staff staff = DQS.findRegisteredStaff(jascId);
        staff.setStaffClinicId(clinicId);
        staff.setStaffEmail(mail.toLowerCase());
        staff.setStaffFirstName(firstName.toLowerCase());
        staff.setStaffLastName(lastName.toUpperCase());

        try {
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

        return "redirect:/team";
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

        return "redirect:/team";
    }

    @PostMapping("/userlogin")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password){

        if (DQS.validateUserAccountCredentials(username, password))
            return "redirect:/appointments"; // TODO: filter which user is login in to redirect them to the correct url
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

    @PostMapping("/makeAdmin")
    public String makeUserAdmin(@RequestParam("username") String username){

        User user = DQS.findRegisteredUserAccount(username);

        if (user != null) {
            try {
                DEAMS.editUserAccountCredentials(user.getUsername(), user.getPassword(), "admin");
            } catch (PersistenceException exp) {
                //
            } catch (IllegalArgumentException exp) {
                //
            } catch (NullPointerException exp) {
                //
            } catch (Exception exp) {
                //
            }

            return "redirect:/team";
        }
        else
            return "redirect:/team";  // TODO: Implement error exception or message to make admin
    }
}
