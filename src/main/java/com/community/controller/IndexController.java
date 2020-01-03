package com.community.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.community.domain.Question;
import com.community.dto.QuestionDTO;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-19 10:58
 **/
@Controller
public class IndexController {
    private final QuestionService questionService;

    @Autowired
    public IndexController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "5") Integer size,
            Model model){
        Page<Question> questionPage=new Page<>(page,size);
        List<QuestionDTO> questionList = questionService.selectList(questionPage);
        model.addAttribute("questions",questionList);
        questionPage.setTotal(questionService.selectCount(new EntityWrapper<>()));
        model.addAttribute("pages",questionPage.getPages());
        return "index";
    }
}
