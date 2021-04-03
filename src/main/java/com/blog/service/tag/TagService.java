package com.blog.service.tag;

import com.blog.pojo.blog.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description
 * @Date 2021/3/29 21:15
 * @Version 1.0
 */
public interface TagService {
    int saveTag(Tag tag);
    
    Tag getTagById(Long id);
    
    int updateTag(Tag tag);
    
    int deleteById(Long id);
    
    PageInfo<Tag> getTagByPageNum(int pageNum,int pageSize);
    
    List<Tag> getAllTag();
    
    List<Tag> listTagByIds(String ids);
    
    //在插入新数据前校验是否有相同的名称
    Tag getTagByName(String name);
    
    List<Tag> getTagTop(int pageSize);
    
}
