package com.clinichelper.Controller;

import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import com.clinichelper.Service.ToolKitService;
import com.clinichelper.Tools.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;
    @Autowired
    private ToolKitService TKS;


    @RequestMapping("/")
    public ModelAndView home(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        if (!DQS.isUserLoggedIn())
        {
            System.out.println("\n\n\n\n\nERROR DETECTING LOGGIN AT INDEX\n\n\n\n\n");
            return new ModelAndView("redirect:/login");
        }
        model.addAttribute("name", name);
        model.addAttribute("todoList", TKS.InitializeTodoList("CH-PLATINUM-JASC"));
        model.addAttribute("todays_appointments", DQS.findAllRegisteredAppointmentsForToday("CH-PLATINUM-JASC"));
        return new ModelAndView("homepage/index");
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