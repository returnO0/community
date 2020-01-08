package com.community.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.community.domain.Comment;
import com.community.domain.User;
import com.community.dto.CommentDTO;
import com.community.enums.CommentType;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.persistence.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author zhaowei.zhang01@hand-china.com
 * @since 2020-01-02 15:38:38
 */
@Service
public class CommentService extends ServiceImpl<CommentMapper,Comment> {
    private final QuestionService questionService;
    private final CommentMapper commentMapper;

    @Autowired(required = false)
    public CommentService(QuestionService questionService, CommentMapper commentMapper) {
        this.questionService = questionService;
        this.commentMapper = commentMapper;
    }

    /**
     * 插入回复
     * @param comment 回复dto
     * @param user 用户
     * @return 是否成功
     */
    private boolean insert(Comment comment,User user) {
        comment.setCommentator(user.getAccountId());
        //创建当前时间
        Date now=new Date();
        //将dto拷贝到实体类中
        comment.setCreateTime(now);
        comment.setUpdateTime(now);
        return super.insert(comment);
    }

    /**
     * 插入回复校验(添加事务管理)
     * @param comment 回复dto
     * @param user 用户
     */
    @Transactional(rollbackFor = CustomizeException.class)
    public void insertComment(Comment comment,User user){
        boolean flag;
        if (StringUtils.isEmpty(comment)||StringUtils.isEmpty(comment.getParentId())){
            //comment为空,或者父id不存在
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }else if (StringUtils.isEmpty(comment.getType())|| !CommentType.isExist(comment.getType())){
            //回复类型为空或者不存在
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }else if (comment.getType().equals(CommentType.COMMENT.getValue())){
            //回复评论
            //评论不存在
            if (StringUtils.isEmpty(selectById(comment.getParentId()))){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            flag=insert(comment,user);
        }else if (StringUtils.isEmpty(comment.getContent())){
            //回复类容为空
            throw new CustomizeException(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }else {
            //回复问题
            //问题不存在
            if (StringUtils.isEmpty(questionService.selectById(comment.getParentId()))){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            flag=insert(comment,user);
        }
        //插入成功则评论数+1,失败抛出异常
        if (flag){
            questionService.incComment(comment.getParentId());
        }else {
            throw new CustomizeException(CustomizeErrorCode.INSERT_DEFAULT);
        }
    }

    /**
     *
     * @param id 问题id或一级评论
     * @param commentType 评论类型(一级评论，二级评论)
     * @return 问题对应一级评论的DTO集合
     */
    public List<CommentDTO> selectListById(Long id, CommentType commentType) {
        return commentMapper.selectCommentById(id,commentType.getType());
    }
}