/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.AppointmentStatus;
import com.clinichelper.Tools.AppointmentType;
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
import java.util.List;

@Controller
public class AppointmentController {

    // Services
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @Autowired
    private ToolKitService TKS;

    // Gets
    @GetMapping("/appointments")
    public ModelAndView fetchAppointmentView(Model model) throws Exception{
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

        model.addAttribute("todoList", TKS.InitializeTodoList(clinicId));
        model.addAttribute("appointmentList", DQS.findAllRegisteredAppointmentsForClinic(clinicId));

        return new ModelAndView("appointments/allAppointment");
    }

    // Posts
    @PostMapping("/newAppointment")
    public String createNewApointment(@RequestParam("appointmentTime") Timestamp appointmentTime, @RequestParam("patient") String patientId, @RequestParam("description") String appointmentDescription, @RequestParam("type") String appointmentType){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
            String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

            Patient patient = DQS.findRegisteredPatientByIdCard(clinicId, patientId);

            DEAMS.createNewAppointment(clinicId, new Date(sdf1.parse(appointmentTime.toString()).getTime()), new Timestamp(sdf2.parse(appointmentTime.toString()).getTime()), patient.getPatientId(), appointmentDescription, appointmentType.toUpperCase().equals("C") ? AppointmentType.CONSULTATION : AppointmentType.SURGERY);

            return "redirect:/appointments";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/appointments"; // TODO: add error message handling
    }

    @PostMapping("/cancelAppointment")
    public String cancelRegisteredAppointment(@RequestParam("id") String appointmentId){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            DEAMS.deleteRegisteredAppointment(appointmentId);
            return "redirect:/appointments";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/appointments"; // TODO: add error message handling
    }

    @PostMapping("/changeDateAndTime")
    public String editDateAndTimeOfRegisteredAppointment(@RequestParam("id") String appointmentId, @RequestParam("date") String newDate, @RequestParam("time") String newTime){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        Appointment appointment = DQS.findRegisteredAppointment(appointmentId);

        try {

            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            appointment.setAppointmentDate(new Date(sdf1.parse(newDate).getTime()));

            sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            appointment.setAppointmentTime(new Timestamp(sdf1.parse(newTime).getTime()));

            DEAMS.editAppointment(appointment);
            return "redirect:/appointments";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/appointments"; // TODO: add error message handling
    }
}
