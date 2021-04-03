package com.blog.service.achive;

import com.blog.pojo.blog.Blog;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2021/4/3 3:36
 * @Version 1.0
 */
public interface ArchiveService {
    Map<String, List<Blog>> archiveBlog();
}
