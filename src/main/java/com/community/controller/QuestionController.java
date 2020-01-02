package com.community.controller;

import com.community.domain.Question;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-26 09:05
 **/
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        //获取question对象
        Question question = questionService.selectById(id);
        if(StringUtils.isEmpty(question)){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        //将question对象转换为前端需要的DTO对象并存在Model中
        model.addAttribute("question",questionService.toQuestionDTO(question));
        return "question";
    }
}
