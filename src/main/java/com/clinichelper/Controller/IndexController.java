package com.clinichelper.Controller;

import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Tools.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;

@Controller
public class IndexController {

    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;

    @RequestMapping("/")
    public ModelAndView home(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        model.addAttribute("name", name);
        model.addAttribute("todoList",DQS.findAllRegisteredCustomTasksForClinic("CH-PLATINUM-JASC")); // TODO: replace this service for ToolKitService
        model.addAttribute("todays_appointments", DQS.findAllRegisteredAppointmentsForToday("CH-PLATINUM-JASC"));
        return new ModelAndView("homepage/index");
    }

    @RequestMapping("/*")
    public ModelAndView err(Model model, @RequestParam(value="name", required=false, defaultValue="404") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("layouts/error");
    }

    @PostMapping("/newTask")
    public String registerNewPatient(
            @RequestParam("title") String title,
            @RequestParam("type") Task type,
            @RequestParam("description") String description,
            @RequestParam("clinic") String clinicId
           )
            {

        try {

           DEAMS.createNewCustomTask(clinicId, title, type, description);
        } catch (PersistenceException | IllegalArgumentException | NullPointerException exp){
            System.out.println("ERROR EN CREAR PACIENTE");
        } catch (Exception exp){
            System.out.println("ERROR EN CREAR PACIENTE");
        }

        return "redirect:/patients";
    }

}