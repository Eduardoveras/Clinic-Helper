/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Tools.AppointmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

        model.addAttribute("appointmentList", DQS.findAllRegisteredAppointmentsForClinic("CH-PLATINUM-JASC"));
        model.addAttribute("amount", DQS.findAllRegisteredAppointmentsForClinic("CH-PLATINUM-JASC").size());


        return new ModelAndView("appointments/allAppointment");
    }

    // Posts
    @PostMapping("/newAppointment")
    public String createNweApointment(@RequestParam("clinic") String clinicId,@RequestParam("date") String appointmentDate, @RequestParam("time") String  appointmentTime, @RequestParam("id") String patientId, @RequestParam("description") String appointmentDescription, @RequestParam("access") String appointmentAccessFrom, @RequestParam("type") String appointmentType){

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

            Patient patient = DQS.findRegisteredPatientByIdCard(clinicId, patientId);

            DEAMS.createNewAppointment(clinicId, new Date(sdf1.parse(appointmentDate).getTime()), new Timestamp(sdf2.parse(appointmentTime).getTime()), patient.getPatientId(), appointmentDescription, appointmentAccessFrom, appointmentType.toLowerCase().equals("consultation") ? AppointmentType.CONSULTATION : AppointmentType.SURGERY);
        } catch (PersistenceException exp){
            //
        } catch(IllegalArgumentException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }
        
        return "redirect:/appointments/" + clinicId;
    }

    @PostMapping("/cancelAppointment")
    public String cancelRegisteredAppointment(@RequestParam("clinic") String clinicId, @RequestParam("id") String appointmentId){

        try {
            DEAMS.deleteRegisteredAppointment(appointmentId);
        } catch (PersistenceException exp){
            //
        } catch(IllegalArgumentException exp){
            //
        } catch (NullPointerException exp) {
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/appointments/" + clinicId;
    }

    @PostMapping("/changeDateAndTime")
    public String editDateAndTimeOfRegisteredAppointment(@RequestParam("id") String appointmentId, @RequestParam("date") String newDate, @RequestParam("time") String newTime){

        Appointment appointment = DQS.findRegisteredAppointment(appointmentId);

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

        return "redirect:/appointments/" + appointment.getClinic().getClinicId();
    }

}
