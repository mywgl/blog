package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Date 2021/4/3 5:24
 * @Version 1.0
 */
@Controller
public class AboutController {
    
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
