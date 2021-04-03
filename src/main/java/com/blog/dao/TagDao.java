package com.blog.dao;

import com.blog.pojo.blog.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {
    int saveTag(Tag tag);
    
    Tag getTagById(Long id);
    
    int updateTag(Tag tag);
    
    int deleteById(Long id);
    
    List<Tag> getAllTag();
    
    List<Tag> listTagByIds(List<Long> ids);
    
    //在插入新数据前校验是否有相同的名称
    Tag getTagByName(String name);
    
    List<Tag> getTagTop(int pageSize);
    
    void deleteCenterTabByTagId(Long tagId);
}
