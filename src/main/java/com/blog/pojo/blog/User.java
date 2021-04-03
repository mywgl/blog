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
public class User {
    private Long id;
    //昵称
    private String nickName;
    //邮箱
    private String email;
    //用户名
    private String userName;
    //密码
    private String password;
    //头像
    private String avatar;
    //类型
    private Integer type;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    // 一个用户可以发布多篇博客一对多关系
    private List<Blog> blogs;
}
