package com.clinichelper.Controller;

import com.clinichelper.Service.DataEntryAndManagementService;
import com.clinichelper.Service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private DataEntryAndManagementService DEAMS;
    @Autowired
    private DataQueryService DQS;

    @RequestMapping("/")
    public ModelAndView home(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        model.addAttribute("name", name);
        model.addAttribute("todoList",DQS.findAllRegisteredCustomTasks());
        return new ModelAndView("homepage/index");
    }


    @RequestMapping("/*")
    public ModelAndView err(Model model, @RequestParam(value="name", required=false, defaultValue="404") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("layouts/error");
    }

}