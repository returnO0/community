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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-25 16:40
 **/
@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/{action}")
    public String profile(@RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "size",defaultValue = "5") Integer size,
                          @PathVariable(name = "action") String action,
                          Model model){
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            Page<Question> questionPage=new Page<>(page,size);
            List<QuestionDTO> questionList = questionService.selectList(questionPage);
            model.addAttribute("questions",questionList);
            questionPage.setTotal(questionService.selectCount(new EntityWrapper<>()));
            model.addAttribute("pages",questionPage.getPages());
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }
}
