package com.blog.controller;

import com.blog.pojo.blog.Tag;
import com.blog.pojo.vo.BlogQuery;
import com.blog.service.blog.BlogService;
import com.blog.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description
 * @Date 2021/4/2 16:09
 * @Version 1.0
 */
@Controller
public class TagShowController {
    private BlogService blogService;
    private TagService tageService;
    
    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
    
    @Autowired
    public void setTageService(TagService tageService) {
        this.tageService = tageService;
    }
    
    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        List<Tag> tags = tageService.getTagTop(100);
        // 第一次从首页请求过来的id默认是-1 默认展示第一个类型的博客
        if (id == -1) {
            id = tags.get(0).getId();
        }
        
        model.addAttribute("tags", tags);
        model.addAttribute("blogs", blogService.getBlogListByTagId(pageNum, pageSize, id));
        model.addAttribute("activeId", id);
        return "tags";
    }
    
    @PostMapping("/tags/{id}")
    public String typesPage(@PathVariable Long id, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("blogs", blogService.getBlogListByTagId(pageNum, pageSize, id));
        model.addAttribute("activeId", id);
        return "tags :: tagsList";
    }
}
