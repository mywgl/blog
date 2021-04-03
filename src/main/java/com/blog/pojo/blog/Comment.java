package com.blog.pojo.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Date 2021/3/25 7:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    //昵称
    private String nickName;
    //邮箱
    private String email;
    //评论内容
    private String content;
    //头像
    private String avatar;
    //创建时间
    private Date createTime;
    // 与blog是一对多的关系 一条评论对应一个博客
    private Blog blog;
    private Long blogId;
    //自关联 父评论下有子评论
    private List<Comment> replyComments;
    
    private Comment parentComment;
    //父评论的名称
    private String parentNickName;
    private Long parentCommentId;
    private String adminComment;
}
