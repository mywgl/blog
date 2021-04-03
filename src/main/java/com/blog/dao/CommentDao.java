package com.blog.dao;

import com.blog.pojo.blog.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    List<Comment> getCommentByBlogId(Long blogId);
    
    void saveComment(Comment comment);
    
    List<Comment> getChildrenCommentByParentId(@Param("blogId") Long blogId,@Param("parId") Long parId);
}
