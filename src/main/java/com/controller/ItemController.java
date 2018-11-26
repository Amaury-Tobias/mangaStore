package com.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
    public ModelAndView getItems(Principal principal,
            @RequestParam(value = "clase", defaultValue = "all") String category) {
        ModelAndView model = new ModelAndView();
        if (category.equals("all")) {
            List<Item> items = itemJDBCTemplate.findAll();
            for (Item item : items) {
                if (principal.getName().equals(item.getOwner())) {
                    item.setOwnerBool(true);
                } else {
                    item.setOwnerBool(false);
                }
            }
            model.addObject("items", items);
            model.setViewName("items");
        } else {
            List<Item> items = itemJDBCTemplate.findAllCategory(category);
            for (Item item : items) {
                if (principal.getName().equals(item.getOwner())) {
                    item.setOwnerBool(true);
                } else {
                    item.setOwnerBool(false);
                }
            }
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

    @GetMapping("/createItem")
    @PreAuthorize("!isAnonymous()")
    public ModelAndView createItem() {
        ModelAndView model = new ModelAndView();
        Item item = new Item();
        model.addObject("item", item);
        model.setViewName("crateItem");
        return model;
    }

    @PostMapping("/addItem")
    public String addItem(Principal principal, Item item, @RequestParam("img1") MultipartFile img1,
            @RequestParam("img2") MultipartFile img2, @RequestParam("img3") MultipartFile img3) throws IOException {

        String user = principal.getName();
        item.setPicture1(img1.getBytes());
        item.setPicture2(img2.getBytes());
        item.setPicture3(img3.getBytes());
        itemJDBCTemplate.create(item, user);

        return "redirect:/";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "textSearch", defaultValue = "") String textSearch) {
        ModelAndView model = new ModelAndView();

        List<Item> items = itemJDBCTemplate.search(textSearch);

        model.addObject("category", textSearch);
        model.addObject("items", items);
        model.setViewName("items");

        return model;
    }
}