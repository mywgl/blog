package com.blog.pojo.log;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description
 * @Date 2021/3/24 18:06
 * @Version 1.0
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestLog {
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;
}
