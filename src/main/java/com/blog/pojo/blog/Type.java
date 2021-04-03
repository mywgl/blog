package com.blog.pojo.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description
 * @Date 2021/3/25 7:16
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    //与blog是一对多的关系
    private List<Blog> blogs;
}
