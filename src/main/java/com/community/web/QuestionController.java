package com.community.web;

import com.community.domain.Question;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (Question)表控制层
 *
 * @author zhaowei.zhang01@hand-china.com
 * @since 2019-12-24 14:29:28
 */
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


}