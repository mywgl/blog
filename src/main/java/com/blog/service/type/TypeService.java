package com.blog.service.type;

import com.blog.pojo.blog.Type;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {
    int saveType(Type type);
    
    Type getTypeById(Long id);
    
    int updateType(Type type);
    
    int deleteById(Long id);
    
    PageInfo<Type> getTypeByPageNum(int pageNum, int pageSize);
    
    List<Type> getAllType();
    
    //通过名称查询type 校验在新增前是否有相同的名称
    Type getTypeByName(String name);
    
    List<Type> getTypeTop(int pageSize);
    
}
