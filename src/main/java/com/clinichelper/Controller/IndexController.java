package com.clinichelper.Controller;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Entity.Contact;
import com.clinichelper.Entity.Meeting;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.AppointmentStatus;
import com.clinichelper.Tools.Enums.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class IndexController implements ErrorController {

    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @Autowired
    private ToolKitService TKS;
    private static final String ERR_PATH = "/error";


    @RequestMapping("/")
    public ModelAndView home(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
        
        try {
            List<Appointment> appointments = DQS.findAllRegisteredAppointmentsForToday(clinicId);
            model.addAttribute("todays_appointments", DQS.findAllRegisteredAppointmentsForToday(clinicId));
        } catch (Exception exp){
            model.addAttribute("todays_appointments", new ArrayList<Appointment>()); // An error occurred to make the list empty
        }

        model.addAttribute("name", name);
        model.addAttribute("todoList", TKS.InitializeTodoList(clinicId));
        //model.addAttribute("pending", countConditions(appointments, AppointmentStatus.PENDING));
        //model.addAttribute("inOffice", countConditions(appointments, AppointmentStatus.IN_OFFICE));
        //model.addAttribute("completed", countConditions(appointments, AppointmentStatus.COMPLETED));
        model.addAttribute("user",DQS.getSessionAttr("user"));
        model.addAttribute("events",TKS.InitializeClinicCalendar(clinicId));

        return new ModelAndView("homepage/index");
    }

    @RequestMapping(value = ERR_PATH)
    public String error() {
        return "layouts/error";
    }


    // Posts
    @PostMapping("/newTask")
    public String registerNewTask(@RequestParam("title") String title, @RequestParam("type") Task type, @RequestParam("description") String description) {

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            DEAMS.createNewCustomTask(DQS.getCurrentLoggedUser().getClinic().getClinicId(), title, type, description);
            return "redirect:/";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            System.out.println("ERROR EN CREAR TASK");
        } catch (Exception exp){
            System.out.println("ERROR EN CREAR TASK");
        }

        return "redirect:/"; // TODO: add error handling method
    }

    @PostMapping("/newMeeting")
    public String registerNewMeeting(@RequestParam("title") String title, @RequestParam("objective") String objective, @RequestParam("time")Timestamp time, @RequestParam("place")String place, @RequestParam("attendees") List<String> attendees){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        List<Contact> team = new ArrayList<>();

        try {
            for (String s:
                 attendees) {
                team.add(DQS.findRegisteredStaffByEmail(DQS.getCurrentLoggedUser().getClinic().getClinicId(), s));
            }

            DEAMS.createNewMeeting(DQS.getCurrentLoggedUser().getClinic().getClinicId(), title, objective, time, place, new HashSet<>(team));
            return "redirect:/";
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/"; // TODO: add error handling method
    }

    @PostMapping("/cancelMeeting")
    public String deleteMeeting(@RequestParam("id") String meetingId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try {
            DEAMS.deleteRegisteredMeeting(meetingId);
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/"; // TODO: add error handling method
    }

    @PostMapping("/rescheduleMeeting")
    public String changeMeetingTimeAndDate(@RequestParam("id") String meetingId, @RequestParam("time") Timestamp newTime){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        try{
            Meeting meeting = DQS.findRegisteredMeeting(meetingId);
            meeting.setMeetingTime(newTime);
            DEAMS.editMeeting(meeting);
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            //
        } catch (Exception exp){
            //
        }

        return "redirect:/"; // TODO: add error handling method
    }

    // Auxiliary Functions
    @Override
    public String getErrorPath() {
        return ERR_PATH;
    }

    private int countConditions(List<Appointment> appointments, AppointmentStatus status){
        int num = 0;

        for (Appointment a:
                appointments) {
            if (a.getAppointmentStatus().equals(status))
                num++;
        }

        return num;
    }
}