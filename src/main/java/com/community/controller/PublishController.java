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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-24 11:10
 **/
@Controller
public class PublishController {
    private final QuestionService questionService;

    @Autowired
    public PublishController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    /**
     * 插入问题
     * @param question 问题对象
     * @param request 用于获取session
     * @param model 返回页面错误信息
     * @return 跳回publish
     */
    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model){
        //取出session中的user
        Object obj = request.getSession().getAttribute("user");
        //判断对象是否为空,并且属于User对象
        if (!StringUtils.isEmpty(obj)&& obj instanceof User){
            User user= (User) obj;
            //设置创建人的id
            question.setCreator(user.getAccountId());
            boolean flag = questionService.insertOrUpdate(question);
            if (flag){
                return "redirect:/";
            }else {
                model.addAttribute("err","插入或更新失败");
            }
        }else {
            //用户为空,未登录 返回publish页面
            model.addAttribute("err","用户未登录");
        }
        return "publish";
    }

    /**
     * 编辑问题
     * @param id 问题id
     * @param model 数据模型
     * @return  跳回publish页面
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Long id,
                       Model model){

        Question question=questionService.selectById(id);
        model.addAttribute("id",id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        return "publish";
    }


}
