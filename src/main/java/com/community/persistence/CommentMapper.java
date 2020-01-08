package com.community.persistence;

import com.community.domain.Comment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.community.dto.CommentDTO;

import java.io.Serializable;
import java.util.List;

/**
 * (Comment)表Mapper接口
 *
 * @author zhaowei.zhang01@hand-china.com
 * @since 2020-01-02 15:38:40
 */
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 查询相关问题所有评论
     * @param id 问题id或一级评论id
     * @param commentType 评论类型(一级评论，二级评论)
     * @return 评论dto集合
     */
    List<CommentDTO> selectCommentById(Long id, Integer commentType);
}