package com.blog.service.tag;

import com.blog.dao.TagDao;
import com.blog.exception.NotFoundException;
import com.blog.pojo.blog.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2021/3/29 21:15
 * @Version 1.0
 */
@Service
public class TagServiceImpl implements TagService{
    private TagDao tagDao;
    
    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }
    
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }
    
    @Override
    public Tag getTagById(Long id) {
        return tagDao.getTagById(id);
    }
    
    @Override
    public int updateTag(Tag tag) {
        if(tagDao.getTagById(tag.getId())==null)
            throw new NotFoundException("不存在该类型!");
        return tagDao.updateTag(tag);
    }
    
    @Override
    public int deleteById(Long id) {
        //同时删除中间表关联数据
        tagDao.deleteCenterTabByTagId(id);
        return tagDao.deleteById(id);
    }
    
    @Override
    public PageInfo<Tag> getTagByPageNum(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "id desc");
        List<Tag> tags = tagDao.getAllTag();
        return new PageInfo<Tag>(tags);
    }
    
    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }
    
    @Override
    public List<Tag> listTagByIds(String ids) {
        if (ids==null || "".equals(ids) ){
           return null;
        }
        List<Long> list =Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return tagDao.listTagByIds(list);
    }
    
    @Override
    public Tag getTagByName(String name) {
      return tagDao.getTagByName(name);
    }
    
    @Override
    public List<Tag> getTagTop(int pageSize) {
        return tagDao.getTagTop(pageSize);
    }
}
