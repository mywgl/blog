package com.blog.service.comment;

import com.blog.pojo.blog.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> commentByBlogId(Long blogId);
    
    void saveComment(Comment comment);
    
}
