package com.blog.controller.admin;

import com.blog.pojo.blog.Blog;
import com.blog.pojo.blog.Tag;
import com.blog.pojo.blog.User;
import com.blog.pojo.vo.BlogQuery;
import com.blog.service.blog.BlogService;
import com.blog.service.tag.TagService;
import com.blog.service.type.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Date 2021/3/28 16:49
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    private BlogService blogService;
    private TypeService typeService;
    private TagService tagService;
    
    private  final String REDIRECT =  "redirect:/admin/blogs";
    private  final String BLOGS =  "/admin/blogs";
    private  final String INPUT =  "/admin/blogs-input";
    
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
    
    //初始化博客后台管理
    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1") int pageNum,
                        @RequestParam(defaultValue = "2") int pagSeize, BlogQuery blog, Model model) {
        
        PageInfo<Blog> pageInfo = blogService.getAllBlogByBlogQuery(pageNum, pagSeize, blog);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("pageInfo", pageInfo);
        return BLOGS;
    }
    
    // 局部刷新不用重新渲染页面而是更换部分数据
    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "2") int pagSeize, BlogQuery blog, Model model) {
        PageInfo<Blog> pageInfo = blogService.getAllBlogByBlogQuery(pageNum, pagSeize, blog);
        model.addAttribute("pageInfo", pageInfo);
        //返回的是局部片段 这是thymeleaf的语法
        return "/admin/blogs :: blogList";
    }
    
    //跳转博客发布页面
    @GetMapping("/blogs/input")
    public String blogsInput(Model model) {
        model.addAttribute("blog", new Blog());
        getTypesAndTags(model);
        return INPUT;
    }
    
    //获取要修改的博客返回前端
    @GetMapping("/blogs/{id}/edit")
    public String getUpdateBlog(@PathVariable long id, Model model) {
        getTypesAndTags(model);
        Blog blog = blogService.getBlogById(id);
        String tagIds = blogService.getTagByBlogId(blog.getId());
        blog.setTagIds(tagIds);
        model.addAttribute("blog", blog);
        return INPUT;
    }
    
    //抽取公共的方法
    private void getTypesAndTags(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }
    
    // 新增博客
    @PostMapping("/blogs")
    public String insertBlog(Blog blog, RedirectAttributes attributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        blog.setType(typeService.getTypeById(blog.getTypeId()));
        //支持在写博客时新增自定义的标签
        insertTag(blog);
        blog.setTags(tagService.listTagByIds(blog.getTagIds()));
        int re = blogService.saveBlog(blog);
        
        if (re > 0) {
            // TODO 先插入数据 才能获取自增的id 才能插入到中间表
            blogService.insertCenterTable(blog,null);
            attributes.addFlashAttribute("message", "添加博客成功!");
        } else {
            attributes.addFlashAttribute("message", "添加博客失败!");
        }
        
        return REDIRECT;
    }
    
    //修改博客
    @PostMapping("/blogs/update")
    public String updateBlog(Blog blog, RedirectAttributes attributes, HttpSession session) {
        //维护中间表
        
        //插入自定义的新标签
        insertTag(blog);
        int re = blogService.updateBlog(blog);
        if (re > 0) {
            // update博客的同时也在更新中间表
            blogService.updateCenterTable(blog);
            attributes.addFlashAttribute("message", "修改博客成功!");
        } else {
            attributes.addFlashAttribute("message", "修改博客失败!");
        }
        return REDIRECT;
    }
    
    //判断tagIds中是否有新的自定义标签
    private void insertTag(Blog blog) {
        if (blog.getTagIds() !=null && "".equals(blog.getTagIds())){
            return;
        }
        StringBuilder buf = new StringBuilder();
        String[] split = blog.getTagIds().split(",");
        for (String item : split) {
            try {
                Long.parseLong(item);
                buf.append(item).append(",");
            }catch (Exception e){
                //查询是否有相同的tag 没有就插入新的标签
                if(tagService.getTagByName(item)==null){
                    Tag tag = new Tag();
                    tag.setName(item);
                    tagService.saveTag(tag);
                    
                    if (blog.getId() !=null){
                        blogService.insertCenterTable(blog, tag.getId());
                    }
                    buf.append(tag.getId()).append(",");
                }
            }
            
        }
        String ids = buf.toString();
        if (ids.endsWith(",")){
            ids = ids.substring(0, ids.length()-1);
        }
        blog.setTagIds(ids);
    }
    
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        int re = blogService.deleteBlog(id);
        if (re > 0) {
            attributes.addFlashAttribute("message", "删除博客成功!");
        } else {
            attributes.addFlashAttribute("message", "删除博客失败!");
        }
        return REDIRECT;
    }
    
}
