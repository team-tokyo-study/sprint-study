package com.example.newbie.controller;

import com.example.newbie.dto.CreateDTO;
import com.example.newbie.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewbieController {
    @GetMapping("/mypage")

    public String hello(Model model) {
        model.addAttribute("name", "mypage");
        return "mypage";
    }
        @GetMapping("/mypage")
        public String mypage(Model model){
            model.addAttribute("name", "mypage model");
            return "mypage";
        }
    @PostMapping("/mypage/create")
    public String createArticle(CreateDTO form) {
        System.out.println(form.toString());
        Article article = form.toEntity();
        System.out.println(article.toString());
        return "";
    }
}
