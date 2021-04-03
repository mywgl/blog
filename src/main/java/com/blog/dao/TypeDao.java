package com.blog.dao;

import com.blog.pojo.blog.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao {
    int saveType(Type type);
    
    Type getTypeById(Long id);
    
    int updateType(Type type);
    
    int deleteById(Long id);
    
    List<Type> getAllType();
    
    List<Type> getTypeTop(int pageSize);
    
    //在插入新数据前校验是否有相同的名称
    Type getTypeByName(String name);
}
