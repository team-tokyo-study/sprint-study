package com.example.newbie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewbieController {
    @GetMapping("/jaehoon")
    public String hello(Model model){
        model.addAttribute("name","jaehoon model");
        return "jaehoon";
    }


}
