package com.blog.pojo.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description
 * @Date 2021/3/25 7:18
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    //id
    private Long id;
    //标签名
    @NotBlank(message = "标签名不能为空")
    private String name;
    //与blog是多对多的关系
    private List<Blog> blogs;
}
