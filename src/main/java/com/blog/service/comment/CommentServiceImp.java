package com.blog.service.comment;

import com.blog.dao.CommentDao;
import com.blog.pojo.blog.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Date 2021/4/1 17:13
 * @Version 1.0
 */
@Service
public class CommentServiceImp implements CommentService {
    private CommentDao commentDao;
    
    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    
    @Override
    public List<Comment> commentByBlogId(Long blogId) {
        
        // 获取父评论
        List<Comment> comments = commentDao.getCommentByBlogId(blogId);
        for (Comment comment : comments) {
            ArrayList<Comment> queryChildrenComments = new ArrayList<>();
            // 查找子评论
            getChildrenComment(blogId,comment.getId(),comment.getNickName(),comment,queryChildrenComments);
            comment.setReplyComments(queryChildrenComments);
        }
    
        
        return  comments;
    }
    /***
     * @Description
     *  递归添加每个父级评论下的所有子评论到集合中
     * @Date 2021/4/2 12:33
     * @param blogId 博客id
     * @param parId 父评论id
     * @param parNickName 父评论名称
     * @param parComment  父评集合
     * @param result 把在这个父评论下的所有层级的子评论都添加到这个集合中
     * @return void
     */
    private void getChildrenComment(Long blogId,Long parId,String parNickName,Comment parComment,ArrayList<Comment> result ) {
        //获取一级评论
        List<Comment> childrenComment = commentDao.getChildrenCommentByParentId(blogId, parId);
        
        if (childrenComment.size()>0){
            for (Comment comment : childrenComment) {
                getChildrenComment(blogId, comment.getId(),comment.getNickName(),comment,result);
                // 在子评论的开始内容中加入 要回复人的名称 排除自已给自已的回复名称
                // if(!parNickName.equals(comment.getNickName())){
                //     String content = comment.getContent();
                //     comment.setContent("回复 @" + parNickName + ":" +content);
                // }
                if (!parNickName.equals(comment.getNickName())){
                    comment.setParentNickName(parNickName);
                }
                result.add(comment);
            }
        }

    }
    
    @Override
    public void saveComment(Comment comment) {
         commentDao.saveComment(comment);
    }
}
