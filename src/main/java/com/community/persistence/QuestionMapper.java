package com.community.persistence;

import com.community.domain.Question;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * (Question)表Mapper接口
 *
 * @author zhaowei.zhang01@hand-china.com
 * @since 2019-12-24 14:29:28
 */
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 增加阅读数
     * @param id 主键
     */
    void incViewCount(Long id);

    /**
     * 增加评论数
     * @param id 主键
     */
    void incCommentCount(Long id);
}