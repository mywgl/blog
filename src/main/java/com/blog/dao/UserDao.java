package com.blog.dao;

import com.blog.pojo.blog.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Date 2021/3/24 11:49
 * @Version 1.0
 */
@Repository
public interface UserDao {
   User findByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);
}
