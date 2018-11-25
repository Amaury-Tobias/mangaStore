package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.item.Item;
import models.item.ItemJDBCTemplate;

@Component
@Controller
public class ItemController {

    @Autowired
    ItemJDBCTemplate itemJDBCTemplate;

    @GetMapping("/items")
    public ModelAndView getItems(@RequestParam(value = "clase", defaultValue = "all") String clase) {
        ModelAndView model = new ModelAndView();
        List<Item> items = itemJDBCTemplate.findAll();
        model.addObject("items", items);
        model.addObject("clase", clase);
        model.setViewName("items");
        return model;
    }

    @GetMapping("/item/image")
    public @ResponseBody byte[] getItemImage(@RequestParam(value = "id", defaultValue = "0") int id,
            @RequestParam(value = "", defaultValue = "0") int pos) {
        Item item = itemJDBCTemplate.getItemImage(id);
        byte[] image = new byte[1];
        switch (pos) {
        case 1:
            image = item.getPicture1();
            break;
        case 2:
            image = item.getPicture2();
            break;
        case 3:
            image = item.getPicture3();
            break;
        }
        return image;
    }
}