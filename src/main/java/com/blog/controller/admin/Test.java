package com.blog.controller.admin;

import com.blog.pojo.blog.Blog;

/**
 * @Description
 * @Date 2021/3/30 16:37
 * @Version 1.0
 */
public class Test {
    private static String insertTag(Blog blog) {
        if (blog.getTagIds() !=null && "".equals(blog.getTagIds())){
            return null;
        }
        StringBuilder buf = new StringBuilder();
        String[] split = blog.getTagIds().split(",");
        for (String item : split) {
            try {
                Long.parseLong(item);
                buf.append(item).append(",");
            }catch (Exception e){
                //查询是否有相同的tag 没有就插入新的标签
                // if(tagService.getTagByName(item)==null){
                //     Tag tag = new Tag();
                //     tag.setName(item);
                //     tagService.saveTag(tag);
                // }
            }
            
        }
        String ids = buf.toString();
        if (ids.endsWith(",")){
            ids = ids.substring(0, ids.length()-1);
        }
        // blog.setTagIds(ids);
        return  ids;
    }
    
    public static void main(String[] args) {
        Blog blog = new Blog();
        blog.setTagIds("1,2,45,自定义");
        System.out.println(insertTag(blog));
    }
}
