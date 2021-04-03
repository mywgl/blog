package com.blog.service.type;

import com.blog.dao.TypeDao;
import com.blog.exception.NotFoundException;
import com.blog.pojo.blog.Type;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Date 2021/3/28 19:25
 * @Version 1.0
 */
@Service
public class TypeServiceImpl implements TypeService{
    private TypeDao typeDao;
    
    @Autowired
    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }
    
    //开启事务
    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }
    
    @Override
    public Type getTypeById(Long id) {
        return typeDao.getTypeById(id);
    }
    
    @Transactional
    @Override
    public int updateType(Type type) {
        if(typeDao.getTypeById(type.getId())==null)
            throw new NotFoundException("不存在该类型!");
        return typeDao.updateType(type);
    }
    @Transactional
    @Override
    public int deleteById(Long id) {
        
        return typeDao.deleteById(id);
    }
    
    @Override
    public  PageInfo<Type> getTypeByPageNum(int pageNum,int pageSize) {
        //使用分页插件 开启分页 倒序排序
        PageHelper.startPage(pageNum,pageSize, "id desc");
        List<Type> typeList = typeDao.getAllType();
        return  new PageInfo<Type>(typeList);
    }
    
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }
    
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }
    
    @Override
    public List<Type> getTypeTop(int pageSize) {
        return typeDao.getTypeTop(pageSize);
    }
}
