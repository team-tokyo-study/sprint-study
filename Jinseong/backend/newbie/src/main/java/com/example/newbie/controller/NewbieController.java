package com.example.newbie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewbieController {
    @GetMapping("/jinseong")

    public String hello(Model model) {
        model.addAttribute("name", "jinseong model");
        return "jinseong";

        @GetMapping("/mypage")
        public String mypage(Model model){
            model.addAttribute("name", "mypage model");

            return "mypage";
        }
    }
}
