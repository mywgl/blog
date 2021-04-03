package com.blog.controller;

import com.blog.service.achive.ArchiveService;
import com.blog.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Date 2021/4/3 3:33
 * @Version 1.0
 */
@Controller
public class ArchiveShowController {
    private ArchiveService archiveService;
    private BlogService blogService;
    @Autowired
    public void setArchiveService(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }
    
    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
    
    @GetMapping("/archives")
    public String archive(Model model){
        model.addAttribute("archiveMap", archiveService.archiveBlog());
        model.addAttribute("blogs", blogService.getFirstPageBlog(1, 10000));
        return "archives";
    }
}
