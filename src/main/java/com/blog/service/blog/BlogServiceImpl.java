package com.blog.service.blog;

import com.blog.dao.BlogDao;
import com.blog.exception.NotFoundException;
import com.blog.pojo.blog.Blog;
import com.blog.pojo.blog.Tag;
import com.blog.pojo.vo.BlogQuery;
import com.blog.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2021/3/29 14:32
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {
    private BlogDao blogDao;
    
    @Autowired
    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }
    
    @Override
    public PageInfo<Blog> getAllBlogByBlogQuery(int pageNum, int pageSize, BlogQuery blog) {
        try {
            PageHelper.startPage(pageNum, pageSize,"update_time desc" );
            return new PageInfo<Blog>(blogDao.getAllBlogByBlog(blog));
        } finally {
            PageHelper.clearPage();
        }
    }
    
    @Override
    public PageInfo<Blog> getFirstPageBlog(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize,"tb.update_time desc" );
            return new PageInfo<Blog>( blogDao.getFirstPageBlog());
        } finally {
            PageHelper.clearPage();
        }
    }
    
    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setViews(0);
        return blogDao.saveBlog(blog);
    }
    
    
    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        //先查询再修改博客
        Blog oldBlog = blogDao.getBlogById(blog.getId());
        if (oldBlog == null) {
            throw new NotFoundException("该博客不存在!");
        }
        // 拷贝对象 oldBlog 拷贝到 blog
        // BeanUtils.copyProperties(oldBlog, blog, MyBeanUtils.getNullPropertyNames(blog));
        return blogDao.updateBlog(blog);
    }
    
    @Transactional
    @Override
    public int deleteBlog(Long id) {
        //删除中间表的维护数据
        blogDao.deleteCenterTableByBlogId(id);
        return blogDao.deleteBlog(id);
    }
    
    @Transactional
    @Override
    public void insertCenterTable(Blog blog, Long tagId) {
        // 此条件在更新博客自定义的标签名中使用
        if (tagId != null) {
            blogDao.insertCenterTable(blog.getId(), tagId);
        } else {
            blog.getTags().forEach((tag) -> {
                        try {
                            blogDao.insertCenterTable(blog.getId(), tag.getId());
                        } catch (Exception ignored) {
                        }
                    }
            );
        }
    }
    
    @Override
    public String getTagByBlogId(Long blogId) {
        StringBuilder str = new StringBuilder("");
        List<Tag> tags = blogDao.getTagByBlogId(blogId);
        tags.forEach(tag -> {
            if (!tag.equals(tags.get(tags.size() - 1))) {
                str.append(tag.getId()).append(",");
            } else {
                str.append(tag.getId());
            }
        });
        return str.toString();
    }
    
    // 更新中间表
    @Override
    public void updateCenterTable(Blog blog) {
        //标签比对原来的是否需要删除
        String ids = blog.getTagIds();
        Long blogId = blog.getId();
        List<Long> TagIdsToList = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        blogDao.deleteCenterTable(blogId, TagIdsToList);
        
        //查询是否有新的标签插入到博客中
        TagIdsToList.forEach(id -> {
            //如果等于null 就是查询不出结果说明此博客没有这个标签
            if (blogDao.getResultByBlogIdAndTagId(blogId, id) == null) {
                blogDao.insertCenterTable(blogId, id);
            }
        });
    }
    
    @Override
    public List<Blog> getRecommendBlog(int pageSize) {
        return blogDao.getRecommendBlog(pageSize);
    }
    
    @Override
    public PageInfo<Blog> getBlogBySearch(int pageNum, int pageSize, String query) {
        try {
            PageHelper.startPage(pageNum, pageSize,"tb.update_time desc" );
            return new PageInfo<Blog>( blogDao.getBlogBySearch(query));
        } finally {
            PageHelper.clearPage();
        }
    }
    
    @Override
    public PageInfo<Blog> getBlogByTypeId(int pageNum, int pageSize, Long typeId) {
        try {
            PageHelper.startPage(pageNum, pageSize,"tb.update_time desc" );
            return new PageInfo<Blog>( blogDao.getBlogByTypeId(typeId));
        } finally {
            PageHelper.clearPage();
        }
    }
    
    @Override
    public PageInfo<Blog> getBlogListByTagId(int pageNum, int pageSize, Long tagId) {
        try {
            PageHelper.startPage(pageNum, pageSize,"tb.update_time desc" );
            return new PageInfo<Blog>( blogDao.getBlogListByTagId(tagId));
        } finally {
            PageHelper.clearPage();
        }
    }
    
    
    @Override
    public Blog getBlogById(Long id) {
        //请求一次浏览数+1
        blogDao.autoIncrementViews(id);
        Blog blog = blogDao.getBlogById(id);
        String content = blog.getContent();
        if (content == null) {
            throw new NotFoundException("该博客资源不存在");
        }
        String contentToHTML = MarkdownUtils.markdownToHtmlExtensions(content);
        blog.setContent(contentToHTML);
        
        return blog;
    }
    
}
