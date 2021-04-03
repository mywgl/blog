package com.blog.dao;

import com.blog.pojo.blog.Blog;
import com.blog.pojo.blog.Tag;
import com.blog.pojo.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogDao {
    Blog getBlogById(Long id);
    
    //同过不同的筛选条件获取所有的Blog进行分页展示
    List<Blog> getAllBlogByBlog(BlogQuery blog);
    
    List<Blog> getFirstPageBlog();
    
    int saveBlog(Blog blog);
    
    int updateBlog(Blog blog);
    
    int deleteBlog(Long id);
    
    //插入中间表
    void insertCenterTable(@Param("blogId") Long blogId, @Param("tagId") Long tagId);
    
    //根据blogId 查询 相应的 Tag
    List<Tag> getTagByBlogId(Long blogId);
    
    //删除对应的中间表数据
    void deleteCenterTable(@Param("blogId") Long blogId,@Param("tagIds") List<Long> tagIds);
    
    //根据两个id 查询是否有结果
    Long getResultByBlogIdAndTagId(@Param("blogId") Long blogId, @Param("tagId") Long tagId);
    
    //当删除博客时中间表的数据不再需要维护需要清除掉
    void deleteCenterTableByBlogId(Long blogId);
    
    //获取被推荐的博客
    List<Blog> getRecommendBlog(int pageSize);
    
    List<Blog> getBlogBySearch(String query);
    //自增浏览数
    void autoIncrementViews(Long blogId);
    
    List<Blog> getBlogByTypeId(Long typeId);
    
    List<Blog> getBlogListByTagId(Long tagId);
    
    List<String> getYear();
    
    List<Blog> getBlogByYear(String year);
}
