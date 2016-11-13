package com.clinichelper.Controller;

import com.clinichelper.Entity.Clinic;
import com.clinichelper.Entity.Contact;
import com.clinichelper.Entity.Meeting;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Eduardo veras on 07-Nov-16.
 */
@Controller
public class MeetingAndContactsController {
    // Services
    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @Autowired
    private ToolKitService TKS;

    // Gets
    @RequestMapping("/contacts")
    public ModelAndView FetchContactsView(Model model){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
        model.addAttribute("todoList", TKS.InitializeTodoList(DQS.getCurrentLoggedUser().getUserId()));
        model.addAttribute("contactList", DQS.findAllRegisteredContactsForClinic(clinicId));

        return new ModelAndView("contacts/allContacts");
    }

    @RequestMapping("/meetings")
    public ModelAndView FetchMeetings(Model model){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
        model.addAttribute("todoList", TKS.InitializeTodoList(DQS.getCurrentLoggedUser().getUserId()));
        model.addAttribute("meetingsList", DQS.findAllRegisteredMeetingsForClinic(clinicId));

        return new ModelAndView("meetings/allMeetings");
    }

    // Posts
    @PostMapping("/new_meeting")
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

}
