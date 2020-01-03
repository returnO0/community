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
import org.springframework.util.StringUtils;

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
    private final UserService userService;
    private final QuestionMapper questionMapper;
    @Autowired(required = false)
    public QuestionService(UserService userService, QuestionMapper questionMapper) {
        this.userService = userService;
        this.questionMapper = questionMapper;
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
            questionDtoList.add(toQuestionDTO(question));
        }
        return questionDtoList;
    }

    /**
     * 封装一个方法 用于将question对象 转换成questionDTO对象
     * @param question question对象
     * @return questionDTO对象
     */
    public QuestionDTO toQuestionDTO(Question question){
        User user=userService.selectOne(new EntityWrapper<User>().eq("account_id",question.getCreator()));
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 插入或更新 (更新时不用修改插入时间)
     * @param entity 问题对象
     * @return 是否成功
     */
    @Override
    public boolean insertOrUpdate(Question entity) {

        boolean flag;
        Date now=new Date();
        if(StringUtils.isEmpty(entity.getId())){
            entity.setCreateTime(now);
            entity.setUpdateTime(now);
            flag=insert(entity);
        }else {
            entity.setUpdateTime(now);
            flag=updateById(entity);
        }
        return flag;
    }

    /**
     * 阅读数+1
     * @param id 问题id
     */
    public void incView(Long id) {
        questionMapper.incViewCount(id);
    }

    /**
     * 增加评论数
     * @param id 主键
     */
    public void incComment(Long id){
        questionMapper.incCommentCount(id);
    }
}