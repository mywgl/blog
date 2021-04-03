package com.blog.controller;

import com.blog.service.blog.BlogService;
import com.blog.service.tag.TagService;
import com.blog.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Date 2021/3/24 14:17
 * @Version 1.0
 */
@Controller
public class IndexController {
    private BlogService blogService;
    private TypeService typeService;
    private TagService tagService;
    
    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
    
    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
    
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
    
    @GetMapping({"/", "/index"})
    public String index(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "6") int pageSize, Model model) {
        model.addAttribute("blogs",blogService.getFirstPageBlog(pageNum, pageSize));
        model.addAttribute("types", typeService.getTypeTop(6));
        model.addAttribute("tags", tagService.getTagTop(10));
        model.addAttribute("recommendBlogs", blogService.getRecommendBlog(8));
        return "/index";
    }
    
    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "2") int pageSize,@RequestParam String query, Model model) {
        model.addAttribute("blogs",blogService.getBlogBySearch(pageNum, pageSize,query));
        model.addAttribute("query", query);
        return "/search";
    }
    
    @PostMapping("/searchPage")
    public String searchPage(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "2") int pageSize,@RequestParam String query, Model model) {
        model.addAttribute("blogs",blogService.getBlogBySearch(pageNum, pageSize,query));
        model.addAttribute("query", query);
        return "search :: searchList";
    }
    
    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id,Model model){
        model.addAttribute("blog", blogService.getBlogById(id));
        return "/blog";
    }
    
    @GetMapping("/footer/newblog")
    public String newBlog (Model model){
        model.addAttribute("newBlogs", blogService.getRecommendBlog(3));
        return "commons/common :: newblogList";
    }
}
