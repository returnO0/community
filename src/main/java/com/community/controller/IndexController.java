package com.community.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.community.domain.Question;
import com.community.dto.QuestionDTO;
import com.community.persistence.QuestionMapper;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-19 10:58
 **/
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model){
        Page<Question> page=new Page<>(0,10);
        List<QuestionDTO> questionList = questionService.selectList(page);
        model.addAttribute("questions",questionList);
        return "index";
    }
}
