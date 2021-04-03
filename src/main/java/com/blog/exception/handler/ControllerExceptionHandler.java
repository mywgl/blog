package com.blog.exception.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Date 2021/3/24 15:54
 * @Version 1.0
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        StringBuffer url = request.getRequestURL();
        logger.error("Request URL {}, Exception {}",url ,e);
        //判断异常是否是自定义的异常如果是就抛出交给框架处理否则把异常发送到指定的页面上
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) !=null){
            throw e;
        }
        ModelAndView view = new ModelAndView();
        view.addObject("url", url);
        view.addObject("Exception", e);
        //把异常信息发送到要显示错误的页面
        view.setViewName("error/error");
        return view;
    }
}
