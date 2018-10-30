package com.infodev.pscrms.web;

import com.infodev.pscrms.utilities.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {


    @GetMapping(value = {"/","/login"})
    public  String home(){
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("logggedInUser", Utils.getCurrentlyLoggedInUser());
        return "index";
    }




}
