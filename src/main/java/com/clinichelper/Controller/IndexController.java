package com.clinichelper.Controller;

import com.clinichelper.Entity.Appointment;
import com.clinichelper.Service.AmazonService;
import com.clinichelper.Service.CRUD.DataCreationService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Enums.AppointmentStatus;
import com.clinichelper.Tools.Enums.Permission;
import com.clinichelper.Tools.Enums.Repeat;
import com.clinichelper.Tools.Enums.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController implements ErrorController {

    // Services
    // CRUD
    @Autowired
    private DataCreationService DCS;
    @Autowired
    private DataQueryService DQS;
    //
    @Autowired
    private ToolKitService TKS;
    @Autowired
    private AmazonService AS;
    private static final String ERR_PATH = "/error";

    // Gets
    @RequestMapping("/")
    public ModelAndView assistantHome(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        if (!DQS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        //AS.UploadImageToAWS("YOLO",new File("swag"));

        model.addAttribute("userRole",DQS.getCurrentLoggedUser().getRole());



        String clinicId = DQS.getCurrentLoggedUser().getClinic().getClinicId();
        
        try {
            model.addAttribute("todays_appointments", DQS.findAllRegisteredAppointmentsForToday(clinicId));
        } catch (Exception exp){
            exp.printStackTrace();
            model.addAttribute("todays_appointments", new ArrayList<Appointment>()); // An error occurred to make the list empty
        }

        model.addAttribute("patient_amount",DQS.findAllRegisteredPatientsForClinic(clinicId).size());
        model.addAttribute("users_amount",DQS.findAllAllRegisteredUsersForClinic(clinicId).size());
        model.addAttribute("surg_amount",DQS.findAllRegisteredSurgeries(clinicId).size());
        model.addAttribute("app_today_amount",DQS.findAllRegisteredAppointmentsForToday(clinicId).size());


        model.addAttribute("name", name);
        model.addAttribute("todoList", TKS.InitializeTodoList(DQS.getCurrentLoggedUser().getUserId()));

        model.addAttribute("user",DQS.getSessionAttr("user"));
        model.addAttribute("events",TKS.InitializeClinicCalendar(clinicId));

        if (DQS.getCurrentLoggedUser().getRole() != Permission.ADMIN)
            model.addAttribute("isAdmin", false);
        else
            model.addAttribute("isAdmin", true);

        return new ModelAndView("homepage/index");
    }


    @RequestMapping(value = ERR_PATH)
    public String error() {
        return "layouts/error";
    }

    // Posts
    @PostMapping("/newTask")
    public String registerNewTask(@RequestParam("title") String title, @RequestParam("type") Task type, @RequestParam("repeat")Repeat repeat, @RequestParam("description") String description) {

        if (!DQS.isUserLoggedIn())
            return "redirect:/login";

        //if (DQS.getCurrentLoggedUser().getRole() == Permission.ADMIN)
        // return new ModelAndView("redirect:/");

        try {
            ArrayList<Repeat> reminders = new ArrayList<>();
            reminders.add(repeat);
            DCS.createNewCustomTask(DQS.getCurrentLoggedUser().getUserId(), title, type, description, reminders);
            return "redirect:/";
        } catch (Exception exp){
            System.out.println("ERROR EN CREAR TASK");
            exp.printStackTrace();
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