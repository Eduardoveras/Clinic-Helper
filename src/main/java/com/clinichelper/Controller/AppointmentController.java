/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Controller;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Patient;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.AppointmentType;
import com.clinichelper.Tools.Enums.Permission;
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

        return new ModelAndView("appointments/allAppointment");
    }

    // Posts
    @PostMapping("/newAppointment")
    public String createNewApointment(@RequestParam("appointmentTime") String appointmentTime, @RequestParam("patient") String patientId, @RequestParam("description") String appointmentDescription){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";


        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            System.out.print("shit\n");
            String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
            System.out.print("justtt\n");

            Patient patient = DQS.findRegisteredPatient(patientId);
            System.out.print("hitttt\n");

            Timestamp test= new Timestamp(sdf1.parse(appointmentTime).getTime());
            System.out.print("theeeeee\n");

            System.out.println(clinicId);
            System.out.println(patientId);
            System.out.println(test);
            System.out.println(patient);
            System.out.println(appointmentDescription);

            DEAMS.createNewAppointment(clinicId, test, patient.getPatientId(), appointmentDescription);
            System.out.print("fannnn\n");


            return "redirect:/appointments";
        } catch (Exception exp){
            System.out.println("ERROR MESSAGE:");
            System.out.println(exp.getMessage());
            //
        }

        return "redirect:/appointments"; // TODO: add error message handling
    }

    @PostMapping("/cancelAppointment")
    public String cancelRegisteredAppointment( @RequestParam("appointment_id") String appointmentId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return "redirect:/";

        try {
            DEAMS.deleteRegisteredAppointment(appointmentId);
            return "redirect:/";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/" ; // TODO: add error message handling
    }

    @PostMapping("/changeDateAndTime")
    public String editDateAndTimeOfRegisteredAppointment(@RequestParam("id") String appointmentId, @RequestParam("date") String newDate){

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() == Permission.MEDIC)
            return "redirect:/";

        Appointment appointment = DQS.findRegisteredAppointment(appointmentId);

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            appointment.setAppointmentTime(new Timestamp(sdf1.parse(newDate).getTime()));

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
