/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Appointment;
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
import java.text.SimpleDateFormat;

@Controller
public class AppointmentController {

    // Services
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;

    // Gets
    @GetMapping("/appointments")
    public ModelAndView fetchAppointmentView(Model model) throws Exception{

        model.addAttribute("appointmentList", DQS.findAllRegisteredAppointments());
        model.addAttribute("amount", DQS.findAllRegisteredAppointments().size());

        return new ModelAndView("");
    }

    // Posts
    @PostMapping("/newAppointment")
    public String createNweApointment(@RequestParam("date") String appointmentDate, @RequestParam("time") String  appointmentTime, @RequestParam("jascId") String patientJascId, @RequestParam("description") String appointmentDescription, @RequestParam("access") String appointmentAccessFrom){

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

            DEAMS.createNewAppointment(new Date(sdf1.parse(appointmentDate).getTime()), new Timestamp(sdf2.parse(appointmentTime).getTime()), patientJascId, appointmentDescription, appointmentAccessFrom);
        } catch (PersistenceException exp){
            //
        } catch(IllegalArgumentException exp){
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
        } catch(IllegalArgumentException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/appointments";
    }

    @PostMapping("/changeDateAndTime")
    public String editDateAndTimeOfRegisteredAppointment(@RequestParam("jascId") String appointmentJascId, @RequestParam("date") String newDate, @RequestParam("time") String newTime){

        Appointment appointment = DQS.findRegisteredAppointment(appointmentJascId);

        try {

            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            appointment.setAppointmentDate(new Date(sdf1.parse(newDate).getTime()));

            sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            appointment.setAppointmentTime(new Timestamp(sdf1.parse(newTime).getTime()));

            DEAMS.editAppointment(appointment);
        } catch (PersistenceException exp){
            //
        } catch(IllegalArgumentException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/appointments";
    }

}
