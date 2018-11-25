package com.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import models.item.Item;
import models.item.ItemJDBCTemplate;
import user.User;
import user.UserJDBCTemplate;

@Component
@Controller
public class DefaultController {

    @Autowired
    UserJDBCTemplate userJDBCTemplate;
    @Autowired
    ItemJDBCTemplate itemJDBCTemplate;

    @GetMapping("/")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        User user = userJDBCTemplate.getUser("admin");
        List<Item> items = itemJDBCTemplate.findhighlights();
        model.addObject("items", items);
        model.addObject("user", user);
        model.setViewName("index");
        return model;
    }

    @GetMapping("/user/profile")
    public @ResponseBody byte[] getImageAvatar(@RequestParam("username") String username) {
        User user = userJDBCTemplate.getUser(username);
        return user.getAvatar();
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

    @GetMapping("/signup")
    public String users(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView createUser(@Valid User user, BindingResult result,
            @RequestParam("avatar1") MultipartFile avatar, @RequestParam("cover1") MultipartFile cover) {
        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        model.setViewName(result.hasErrors() ? "signup" : "index");
        if (!result.hasErrors()) {
            try {
                if (!avatar.isEmpty() & !cover.isEmpty()) {
                    user.setAvatar(avatar.getBytes());
                    user.setCover(cover.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            userJDBCTemplate.newUser(user);
        }
        return model;
    }

}
