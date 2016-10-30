package com.clinichelper.Controller;

import com.clinichelper.Entity.Staff;
import com.clinichelper.Entity.User;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Tools.Gender;
import com.clinichelper.Tools.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Eduardo veras on 02-Oct-16.
 */

public class TeamController {
    // Services
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;

    // Gets
    @RequestMapping("/team")
    public ModelAndView fetchStaffView(Model model, @RequestParam("clinic") String clinicId){

        model.addAttribute("staffList", DQS.findAllRegisteredStaffs());
        model.addAttribute("userList", DQS.findAllAllRegisteredUsersForClinic(clinicId));

        return new ModelAndView("");
    }

    // Post
    @PostMapping("/newUser")
    public String newUser(@RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("birthDate") String  birthDate, @RequestParam("gender") String gender, @RequestParam("password") String password, @RequestParam("role") String role, @RequestParam("clinic") String clinicId){

        try{
            DEAMS.createNewUserAccount(email, firstName, lastName, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate).getTime()), gender.toLowerCase().equals("female") ? Gender.F : Gender.M, password, role.toLowerCase().equals("medic") ? Permission.MEDIC : Permission.ASSISTANT, clinicId);
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

    @PostMapping("/deleteUser")
    public String deleteUser (@RequestParam("id") String userId){

        try {
            DEAMS.deleteRegisteredUserAccount(userId);
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

    @PostMapping("/makUserAdmin") //Only accessed by the admin of the clinic account
    public String makeUserDamin(@RequestParam("id") String userId){

        User user = DQS.findUserInformation(userId);

        try {
            DEAMS.editUserAccountCredentials(user.getEmail(), user.getClinic().getClinicId(), user.getPassword(), Permission.ADMIN);
            return "redirect:/team";
        } catch (PersistenceException exp){
            //
        } catch (IllegalArgumentException exp) {
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/team"; // TODO: Implement error exception or message to edit password
    }

}
