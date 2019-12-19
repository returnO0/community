package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-19 10:58
 **/
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){return "index";}
}
