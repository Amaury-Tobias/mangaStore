package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.comment.Comment;
import models.comment.CommentJDBCTemplate;
import models.item.Item;
import models.item.ItemJDBCTemplate;

@Component
@Controller
public class ItemController {

    @Autowired
    ItemJDBCTemplate itemJDBCTemplate;
    @Autowired
    CommentJDBCTemplate commentJDBCTemplate;

    @GetMapping(value = "/items")
    public ModelAndView getItems(@RequestParam(value = "clase", defaultValue = "all") String category) {
        ModelAndView model = new ModelAndView();
        if (category.equals("all")) {
            List<Item> items = itemJDBCTemplate.findAll();
            model.addObject("items", items);
            model.setViewName("items");
        } else {
            List<Item> items = itemJDBCTemplate.findAllCategory(category);
            model.addObject("items", items);
            model.setViewName("items");
        }
        model.addObject("category", category);
        return model;
    }

    @GetMapping(value = "/item")
    public ModelAndView getItem(@RequestParam(value = "id", defaultValue = "1") int id) {
        Item item = itemJDBCTemplate.getItemById(id);
        Comment comment = new Comment();
        List<Comment> comments = commentJDBCTemplate.findItemComments(id);
        comments.remove(0);
        ModelAndView model = new ModelAndView();
        model.addObject("item", item);
        model.addObject("comments", comments);
        model.addObject("comment", comment);
        model.setViewName("item");
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