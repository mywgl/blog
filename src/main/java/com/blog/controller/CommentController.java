package com.blog.controller;

import com.blog.pojo.blog.Comment;
import com.blog.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description
 * @Date 2021/4/1 16:50
 * @Version 1.0
 */
@Controller
public class CommentController {
    private CommentService commentService;
    
    @Value("${comment.avatar}")
    private String avatar;
    
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.commentByBlogId(blogId));
        return "blog :: commentList";
    }
    
    @PostMapping("/comments")
    public String post(Comment comment){
        comment.setAvatar(avatar);
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }
    
}
