/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.CRUD.DataCreationService;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.AppointmentStatus;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppointmentController {

    // Services
    @Autowired
    private DataCreationService DCS;
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

        if (DQS.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return new ModelAndView("redirect:/");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();

        model.addAttribute("todoList", TKS.InitializeTodoList(DQS.getCurrentLoggedUser().getUserId()));
        model.addAttribute("appointmentList", DQS.findAllRegisteredAppointmentsForClinic(clinicId));
        model.addAttribute("userList", DQS.findAllRegisteredPatientsForClinic(clinicId));

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("appointments/allAppointment");
    }

    // Posts
    @PostMapping("/newAppointment")
    public String createNewApointment(@RequestParam("appointmentTime") String appointmentTime, @RequestParam("patient") String patientId, @RequestParam("description") String appointmentDescription){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        //if (DQS.getCurrentLoggedUser().getRole() != Permission.ASSISTANT)
          //  return "redirect:/";

        try {
            DCS.createNewAppointment(DQS.getCurrentLoggedUser().getClinic().getClinicId(),  new Timestamp(new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(appointmentTime).getTime()), DQS.findRegisteredPatient(patientId).getPatientId(), appointmentDescription);
            return "redirect:/appointments";
        } catch (Exception exp){
            System.out.println("ERROR MESSAGE:");
            System.out.println(exp.getMessage());
            exp.printStackTrace();
        }

        return "redirect:/appointments"; // TODO: add error message handling
    }

    @PostMapping("/cancelAppointment")
    public String cancelRegisteredAppointment( @RequestParam("appointment_id") String appointmentId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        //if (DQS.getCurrentLoggedUser().getRole() != Permission.ASSISTANT)
        //  return "redirect:/";

        try {
            if (DQS.findRegisteredAppointment(appointmentId).getAppointmentStatus() == AppointmentStatus.PENDING)
                DEAMS.deleteRegisteredAppointment(appointmentId);
            else
                return "redirect:/" ; // TODO: add error message handling

            return "redirect:/";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/" ; // TODO: add error message handling
    }

    @PostMapping("/changeDateAndTime")
    public String editDateAndTimeOfRegisteredAppointment(@RequestParam("id") String appointmentId, @RequestParam("appointmentTime") String newDate){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        //if (DQS.getCurrentLoggedUser().getRole() != Permission.ASSISTANT)
        //  return "redirect:/";

        try {
            Appointment appointment = DQS.findRegisteredAppointment(appointmentId);

            appointment.setAppointmentTime(new Timestamp(new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(newDate).getTime()));

            DEAMS.editAppointment(appointment);
            return "redirect:/appointments";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/appointments"; // TODO: add error message handling
    }
}
