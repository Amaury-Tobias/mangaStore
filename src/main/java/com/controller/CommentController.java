package com.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import models.comment.Comment;
import models.comment.CommentJDBCTemplate;

/**
 * CommentController
 */
@Component
@Controller
public class CommentController {

    @Autowired
    private CommentJDBCTemplate commentJDBCTemplate;

    @PostMapping("/createComment")
    public String createComment(HttpServletRequest request, Comment comment) {

        comment.setDate(new Date().toString());
        commentJDBCTemplate.create(comment);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    
}