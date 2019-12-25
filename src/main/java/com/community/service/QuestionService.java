package com.community.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.community.domain.Question;
import com.community.domain.User;
import com.community.dto.QuestionDTO;
import com.community.persistence.QuestionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Question)表服务接口
 *
 * @author zhaowei.zhang01@hand-china.com
 * @since 2019-12-24 14:29:28
 */
@Service
public class QuestionService extends ServiceImpl<QuestionMapper,Question> {
    @Autowired
    UserService userService;
    /**
     * 将问题发布信息插入到数据库
     * @param entity 发布问题信息
     * @return  是否成功
     */
    @Override
    public boolean insert(Question entity) {
        Date now=new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        return super.insert(entity);
    }

    /**
     * 查询全部问题 并封装成QuestionDTO
     * @param page 分页参数
     * @return  全部问题
     */
    public List<QuestionDTO> selectList(Page<Question> page) {
        List<Question> questionList = selectPage(page).getRecords();
        List<QuestionDTO> questionDtoList=new ArrayList<>();
        for (Question question:questionList) {
            User user=userService.selectOne(new EntityWrapper<User>().eq("account_id",question.getCreator()));
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDtoList.add(questionDTO);
        }
        return questionDtoList;
    }
}