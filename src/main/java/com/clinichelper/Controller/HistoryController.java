/**
 * Created by Djidjelly Siclait on 11/20/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Service.Security.SessionService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoryController {
    // Services
        // Security
    private SessionService sessionService;
    //
    @Autowired
    private ToolKitService TKS;

    // Gets
    @GetMapping("/patient/record/{i}")
    public ModelAndView fetchPatientMedicalRecord(Model model, @PathVariable(value="id") String patientId){

        if (!sessionService.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        //if (DQS.getCurrentLoggedUser().getRole() != Permission.MEDIC)
        //return new ModelAndView("redirect:/");
        //model.addAttribute("isAdmin", false);

        if (sessionService.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("");
    }

    // Posts
}
