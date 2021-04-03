package com.blog.service.user;

import com.blog.dao.UserDao;
import com.blog.pojo.blog.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2021/3/28 11:14
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    public User checkUser(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password);
    }
}
