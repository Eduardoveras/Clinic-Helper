package com.clinichelper.Controller;

import com.clinichelper.Entity.Contact;
import com.clinichelper.Entity.Meeting;
import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);
        
        return new ModelAndView("contacts/allContacts");
    }

    @RequestMapping("/meetings")
    public ModelAndView FetchMeetings(Model model){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
        model.addAttribute("todoList", TKS.InitializeTodoList(DQS.getCurrentLoggedUser().getUserId()));
        model.addAttribute("meetingsList", DQS.findAllRegisteredMeetingsForClinic(clinicId));
        model.addAttribute("contactList", DQS.findAllRegisteredContactsForClinic(clinicId));

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("meetings/allMeetings");
    }

    // Posts
    @PostMapping("/new_contact")
    public String registerNewContact(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("dateOfBirth") String  birthDate, @RequestParam("email") String email){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/contacts";

        try {
            DEAMS.createNewStaffMember(DQS.getCurrentLoggedUser().getClinic().getClinicId(), firstName, lastName, new Date(new SimpleDateFormat("MM/dd/yyyy").parse(birthDate).getTime()), email);
            return "redirect:/contacts";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/contacts"; // TODO: add error handling method
    }

    @PostMapping("/new_meeting")
    public String registerNewMeeting(@RequestParam("title") String title, @RequestParam("objective") String objective, @RequestParam("time")Timestamp time, @RequestParam("place")String place, @RequestParam("attendees") List<String> attendees){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() == Permission.ADMIN)
           return "redirect:/meetings";

        List<Contact> team = new ArrayList<>();

        try {
            for (String s:
                    attendees) {
                team.add(DQS.findRegisteredStaffByEmail(DQS.getCurrentLoggedUser().getClinic().getClinicId(), s));
            }

            DEAMS.createNewMeeting(DQS.getCurrentLoggedUser().getClinic().getClinicId(), title, objective, time, place, new HashSet<>(team));
            return "redirect:/meetings";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/meetings"; // TODO: add error handling method
    }

    @PostMapping("/delete_contact")
    public String deleteContact(@RequestParam("contactId") String contactId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            return "redirect:/contacts";

        if (DQS.findRegisteredContact(contactId).isHasAccount())
            return "redirect:/users"; // TODO: add Not allowed action delete user account first

        try {
            DEAMS.deleteRegisteredStaff(contactId);
            return "redirect:/contacts";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/contacts"; // TODO: add error handling method
    }

    @PostMapping("/cancelMeeting")
    public String deleteMeeting(@RequestParam("id") String meetingId){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        //if (DQS.getCurrentLoggedUser().getRole() == Permission.ADMIN)
          //  return "redirect:/meetings";

        try {
            DEAMS.deleteRegisteredMeeting(meetingId);
            return "redirect:/meetings";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/meetings"; // TODO: add error handling method
    }

    @PostMapping("/rescheduleMeeting")
    public String changeMeetingTimeAndDate(@RequestParam("id") String meetingId, @RequestParam("time") Timestamp newTime){
        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        //if (DQS.getCurrentLoggedUser().getRole() == Permission.ADMIN)
          //  return "redirect:/meetings";

        try{
            Meeting meeting = DQS.findRegisteredMeeting(meetingId);
            meeting.setMeetingTime(newTime);
            DEAMS.editMeeting(meeting);
            return "redirect:/meetings";
        } catch (Exception exp){
            exp.printStackTrace();
        }

        return "redirect:/meetings"; // TODO: add error handling method
    }

}
