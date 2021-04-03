package com.blog.controller.admin;

import com.blog.pojo.blog.Tag;
import com.blog.service.tag.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Description
 * @Date 2021/3/29 21:20
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class TagsController {
    private TagService tagService;
    
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
    
    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1") int pageNum, Model model){
        PageInfo<Tag> info = tagService.getTagByPageNum(pageNum, 5);
        model.addAttribute("pageInfo", info);
        return "admin/tags";
    }
    
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tags-input";
    }
    
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        if(tagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name", "nameError","不能添加重复的标签名称!");
        }
        
        if (result.hasErrors()){
            return "/admin/tags-input";
        }
        
        int i = tagService.saveTag(tag);
        if (i<0){
            attributes.addFlashAttribute("message", "添加失败");
        }else{
            attributes.addFlashAttribute("message", "添加成功!");
        }
        return "redirect:/admin/tags";
    }
    
    @PostMapping("/tags/update")
    public String editPost(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        if(tagService.getTagByName(tag.getName())!=null){
            result.rejectValue("name", "nameError","不能添加重复的标签名称!");
        }
        
        if (result.hasErrors()){
            return "/admin/tags-input";
        }
        
        int i = tagService.updateTag(tag);
        if (i<0){
            attributes.addFlashAttribute("message", "更新失败");
        }else{
            attributes.addFlashAttribute("message", "更新成功!");
        }
        return "redirect:/admin/tags";
    }
    
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        if (tagService.deleteById(id)>0){
            attributes.addFlashAttribute("message", "删除成功!");
        }else {
            attributes.addFlashAttribute("message", "删除失败!");
        }
        return "redirect:/admin/tags";
    }
}
