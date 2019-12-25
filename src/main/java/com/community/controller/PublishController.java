package com.community.controller;

import com.community.domain.Question;
import com.community.domain.User;
import com.community.service.QuestionService;
import com.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-24 11:10
 **/
@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model){
        System.out.println(question);
        //取出session中的user
        Object obj = request.getSession().getAttribute("user");
        //判断对象是否为空,并且属于User对象
        if (!StringUtils.isEmpty(obj)&& obj instanceof User){
            User user= (User) obj;
            //设置创建人的id
            question.setCreator(user.getAccountId());
            boolean insert = questionService.insert(question);
            if (insert){
                return "redirect:/";
            }else {
                model.addAttribute("err","插入失败");
            }
        }else {
            //用户为空,未登录 返回publish页面
            model.addAttribute("err","用户未登录");
        }
        return "publish";
    }
}
