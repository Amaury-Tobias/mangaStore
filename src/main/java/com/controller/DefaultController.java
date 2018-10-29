package com.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Controller
public class DefaultController {
    private String home;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}
