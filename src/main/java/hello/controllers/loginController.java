package hello.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Eduardo veras on 02-Oct-16.
 */
@Controller
public class loginController {

    @RequestMapping("/login")
    public String home(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        model.addAttribute("name", name);
        return "newpatient";
    }
}
