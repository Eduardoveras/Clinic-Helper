package com.clinichelper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView home(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("index");
    }


    @RequestMapping("/*")
    public ModelAndView err(Model model, @RequestParam(value="name", required=false, defaultValue="404") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("404");
    }

}