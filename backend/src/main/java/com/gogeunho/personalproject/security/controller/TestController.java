package com.gogeunho.personalproject.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/")
    public String home() {
        return "main";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
