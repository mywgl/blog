package com.blog.service.achive;

import com.blog.dao.BlogDao;
import com.blog.pojo.blog.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2021/4/3 3:39
 * @Version 1.0
 */
@Service
public class ArchiveServiceImpl implements ArchiveService{
    private BlogDao blogDao;
    
    @Autowired
    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }
    
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        LinkedHashMap<String,  List<Blog> > map = new LinkedHashMap<>();
        blogDao.getYear().forEach(year->{
            map.put(year,blogDao.getBlogByYear(year));
        });
        return map;
    }
}
