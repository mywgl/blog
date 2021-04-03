package com.blog.controller.admin;

import com.blog.pojo.blog.Type;
import com.blog.service.type.TypeService;
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
 * @Date 2021/3/28 20:26
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class TypesController {
    private TypeService typeService;
    
    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
    
    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1") int pageNum, Model model){
        PageInfo<Type> info = typeService.getTypeByPageNum(pageNum, 5);
        model.addAttribute("pageInfo", info);
        return "admin/types";
    }
    
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/types-input";
    }
    
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        if(typeService.getTypeByName(type.getName())!=null){
            result.rejectValue("name", "nameError","不能添加重复的分类名称!");
        }
        
        if (result.hasErrors()){
            return "/admin/types-input";
        }
        
        int i = typeService.saveType(type);
        if (i<0){
          attributes.addFlashAttribute("message", "添加失败");
        }else{
            attributes.addFlashAttribute("message", "添加成功!");
        }
        return "redirect:/admin/types";
    }
    
    @PostMapping("/types/update")
    public String editPost(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        if(typeService.getTypeByName(type.getName())!=null){
            result.rejectValue("name", "nameError","不能添加重复的分类名称!");
        }
        
        if (result.hasErrors()){
            return "/admin/types-input";
        }
        
        int i = typeService.updateType(type);
        if (i<0){
          attributes.addFlashAttribute("message", "更新失败");
        }else{
            attributes.addFlashAttribute("message", "更新成功!");
        }
        return "redirect:/admin/types";
    }
    
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        if (typeService.deleteById(id)>0){
            attributes.addFlashAttribute("message", "删除成功!");
        }else {
            attributes.addFlashAttribute("message", "删除失败!");
        }
        return "redirect:/admin/types";
    }
}
