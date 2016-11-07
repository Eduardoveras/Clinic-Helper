package com.clinichelper.Controller;

import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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



    @RequestMapping("/contacts")
    public ModelAndView FetchContactsView(Model model){
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");


        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
        model.addAttribute("todoList", TKS.InitializeTodoList(clinicId));
        model.addAttribute("contactList", DQS.findAllRegisteredContactsForClinic(clinicId));

        return new ModelAndView("contacts/allContacts");
    }
}
