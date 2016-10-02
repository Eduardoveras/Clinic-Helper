package hello.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String home(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        model.addAttribute("name", name);
        return "index";
    }


    @RequestMapping("/*")
    public String err(Model model, @RequestParam(value="name", required=false, defaultValue="404") String name) {
        model.addAttribute("name", name);
        return "404";
    }

}