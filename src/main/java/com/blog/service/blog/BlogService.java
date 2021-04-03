package com.blog.service.blog;

import com.blog.pojo.blog.Blog;
import com.blog.pojo.vo.BlogQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description
 * @Date 2021/3/29 14:28
 * @Version 1.0
 */
public interface BlogService {
    
    Blog getBlogById(Long blogId);
    
    //同过不同的筛选条件获取所有的Blog进行分页展示
    PageInfo<Blog> getAllBlogByBlogQuery(int pageNum, int pagSize, BlogQuery blog);
    
    PageInfo<Blog> getFirstPageBlog(int pageNum, int pagSize);
    
    int saveBlog(Blog blog);
    
    int updateBlog(Blog blog);
    
    int deleteBlog(Long id);
    
    //插入中间表
    void insertCenterTable(Blog blog,Long tagId);
    
    //根据blogId 查询 Tag
    String getTagByBlogId(Long blogId);
    
    //更新中间表
    void updateCenterTable(Blog blog);
    
    List<Blog> getRecommendBlog(int pageSize);
    
    PageInfo<Blog> getBlogBySearch(int pagNum,int pageSize,String query);
    
    PageInfo<Blog> getBlogByTypeId(int pagNum,int pageSize,Long typeId);
    
    PageInfo<Blog> getBlogListByTagId(int pagNum, int pageSize, Long tagId);
    
}
