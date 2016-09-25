package hello.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }


    @RequestMapping("/")
    public String home(Model model, @RequestParam(value="name", required=false, defaultValue="home") String name) {
        model.addAttribute("name", name);
        return "homepage";
    }


    @RequestMapping("/*")
    public String err(Model model, @RequestParam(value="name", required=false, defaultValue="404") String name) {
        model.addAttribute("name", name);
        return "404";
    }

}