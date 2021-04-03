package com.blog.service.user;

import com.blog.pojo.blog.User;

/**
 * @Description
 * @Date 2021/3/28 11:13
 * @Version 1.0
 */
public interface UserService {
    User checkUser(String userName,String password);
}
