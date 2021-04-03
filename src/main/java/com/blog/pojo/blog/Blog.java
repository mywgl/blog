package com.blog.pojo.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Date 2021/3/25 7:03
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    //id
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //首图
    private String firstPicture;
    //标记 原创/转载/翻译
    private String flag;
    //浏览次数
    private Integer views;
    //是否开启赞赏
    private boolean appreciation;
    //是否开启转载声明
    private boolean sharStatement;
    //是否开启评论
    private boolean commentEnabled;
    //是否发布
    private boolean published;
    //是否推荐
    private boolean recommended;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //与type是一对多的关系
    private Type type;
    private Long typeId;
    //与tag是多对多关系
    private List<Tag> tags;
    private String description;
    private String  tagIds;
    //每篇博客只能一个用户编写与用户是一对多的关系
    private User user;
    private Long userId;
    private List<Comment> comments;
  
}
