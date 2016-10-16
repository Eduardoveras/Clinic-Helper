package com.clinichelper.Controller;

import com.clinichelper.Entity.Staff;
import com.clinichelper.Entity.User;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;

/**
 * Created by Eduardo veras on 02-Oct-16.
 */
@Controller
public class UserController {
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @GetMapping("/user")
    public ModelAndView fetchPatientView(Model model){

        model.addAttribute("usertList", DQS.findAllRegisteredPatients());

        return new ModelAndView("user");
    }

    @PostMapping("/deleteUser")
    public String deleteUser (@RequestParam("username") String username){

        try {
            DEAMS.deleteRegisterdUserAccount(username);
        }
        catch (PersistenceException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }
        return "redirect/user";
    }
    @PostMapping("/editUser")
    public String editUser(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("role") String role
                               ){
        User user = DQS.findRegisteredUserAccount(username);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        try {
            DEAMS.editUserAccount(user);
        }
        catch (PersistenceException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }
        return "redirect:/user";
    }
    @PostMapping("/newUser")
    public String newUSer(@RequestParam("username") String username,
                          @RequestParam("staff") String staff,
                          @RequestParam("password") String password,
                          @RequestParam("role") String role
                          ){
        try{
            DEAMS.createNewUserAccount(username,staff,password,role);
        }
        catch (PersistenceException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }
        return "redirect:/";

    }



}
