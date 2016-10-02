package hello.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eduardo veras on 02-Oct-16.
 */
@Controller
public class registerController {
    @RequestMapping("/solicit")
    public String solicit(){
        return "request";
    }
}
