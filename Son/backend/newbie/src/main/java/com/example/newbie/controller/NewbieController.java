package com.example.newbie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewbieController {
    @GetMapping("/mypage")
    public String hello(Model model){
        model.addAttribute("name","my page");
        return "mypage";
    }


}
