package com.community.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.community.domain.Comment;
import com.community.domain.User;
import com.community.dto.CommentDTO;
import com.community.enums.CommentType;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.persistence.CommentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * (Comment)表服务接口
 *
 * @author zhaowei.zhang01@hand-china.com
 * @since 2020-01-02 15:38:38
 */
@Service
public class CommentService extends ServiceImpl<CommentMapper,Comment> {
    private final QuestionService questionService;

    @Autowired
    public CommentService(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 插入回复
     * @param commentDTO 回复dto
     * @param user 用户
     * @return 是否成功
     */
    private boolean insert(CommentDTO commentDTO,User user) {
        Comment comment = Comment.builder().build();
        comment.setCommentator(user.getAccountId());
        //创建当前时间
        Date now=new Date();
        //将dto拷贝到实体类中
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setCreateTime(now);
        comment.setUpdateTime(now);
        return super.insert(comment);
    }

    /**
     * 插入回复校验(添加事务管理)
     * @param commentDTO 回复dto
     * @param user 用户
     */
    @Transactional(rollbackFor = CustomizeException.class)
    public void insertComment(CommentDTO commentDTO,User user){
        boolean flag;
        if (StringUtils.isEmpty(commentDTO)||StringUtils.isEmpty(commentDTO.getParentId())){
            //commentDTO为空,或者父id不存在
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }else if (StringUtils.isEmpty(commentDTO.getType())|| !CommentType.isExist(commentDTO.getType())){
            //回复类型为空或者不存在
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }else if (commentDTO.getType().equals(CommentType.COMMENT.getValue())){
            //回复评论
            //评论不存在
            if (StringUtils.isEmpty(selectById(commentDTO.getParentId()))){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            flag=insert(commentDTO,user);
        }else {
            //回复问题
            //问题不存在
            if (StringUtils.isEmpty(questionService.selectById(commentDTO.getParentId()))){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            flag=insert(commentDTO,user);
        }
        //插入成功则评论数+1,失败抛出异常
        if (flag){
            questionService.incComment(commentDTO.getParentId());
        }else {
            throw new CustomizeException(CustomizeErrorCode.INSERT_DEFAULT);
        }
    }
}