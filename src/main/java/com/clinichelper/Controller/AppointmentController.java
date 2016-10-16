/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class AppointmentController {

    // Services
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;

    // Gets
    @GetMapping("/appointment")
    public ModelAndView fetchAppointmentView(Model model){

        model.addAttribute("appointments", DQS.findAllRegisteredAppointments());

        return new ModelAndView("appointments");
    }

    // Posts
    @PostMapping("/newAppointment")
    public String createNweApointment(@RequestParam("date") Date appointmentDate, @RequestParam("time") Timestamp appointmentTime, @RequestParam("jascId") String patientJascId, @RequestParam("description") String appointmentDescription, @RequestParam("access") String appointmentAccessFrom){

        try {
            DEAMS.createNewAppointment(appointmentDate, appointmentTime, patientJascId, appointmentDescription, appointmentAccessFrom);
        } catch (PersistenceException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }
        
        return "redirect:/appointments";
    }

    @PostMapping("/cancelAppointment")
    public String cancelRegisteredAppointment(@RequestParam("jascId") String appointmentJascId){

        try {
            DEAMS.deleteRegisteredAppointment(appointmentJascId);
        } catch (PersistenceException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/appointments";
    }

    

}
