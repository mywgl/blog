package com.blog.pojo.vo;

import lombok.Data;

/**
 * @Description
 * @Date 2021/3/29 18:42
 * @Version 1.0
 */
@Data
public class BlogQuery {
    String title;
    Long typeId;
    boolean recommended;
}
