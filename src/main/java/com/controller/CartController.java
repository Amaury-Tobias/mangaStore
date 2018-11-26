package com.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import models.item.Item;
import models.item.ItemJDBCTemplate;

/**
 * CartController
 */
@Component
@Controller
public class CartController {

    @Autowired
    ItemJDBCTemplate itemJDBCTemplate;

    @GetMapping("/cart")
    public ModelAndView cart(Principal principal) {
        ModelAndView model = new ModelAndView();

        String user = principal.getName();
        List<Item> items = itemJDBCTemplate.getCartItems(user);
        model.addObject("items", items);
        model.setViewName("cart");
        return model;
    }

    @GetMapping("/addCart")
    @PreAuthorize("!isAnonymous()")
    public String addCart(Principal principal, @RequestParam(value = "id", defaultValue = "0") int id) {
        String user = principal.getName();
        itemJDBCTemplate.addToCart(id, user);
        return "redirect:/cart";
    }

    @GetMapping("/removeCart")
    @PreAuthorize("!isAnonymous()")
    public String removeCart(Principal principal, @RequestParam(value = "id", defaultValue = "0") int id) {
        String user = principal.getName();
        itemJDBCTemplate.removeCart(id, user);
        return "redirect:/cart";
    }

    @GetMapping("/pay")
    @PreAuthorize("!isAnonymous()")
    public ModelAndView pay(Principal principal) {
        ModelAndView model = new ModelAndView();

        String user = principal.getName();
        List<Item> items = itemJDBCTemplate.getCartItems(user);
        float total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        model.addObject("items", items);
        model.addObject("total", total);
        model.setViewName("bill");

        return model;
    }

    @GetMapping("/checkout")
    @PreAuthorize("!isAnonymous()")
    public String checkout(Principal principal) {
        String user = principal.getName();
        itemJDBCTemplate.checkout(user);
        return "redirect:/";
    }

}