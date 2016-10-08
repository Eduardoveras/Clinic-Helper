package com.clinichelper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Eduardo veras on 02-Oct-16.
 */
@Controller
public class registerController {
    @RequestMapping("/solicit")
    public ModelAndView solicit(Model model){

        return new ModelAndView("newpatient");
    }
}
