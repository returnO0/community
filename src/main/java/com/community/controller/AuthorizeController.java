package com.community.controller;

import com.community.dto.AccessTokenDTO;
import com.community.dto.GitHubUser;
import com.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-19 15:18
 **/
@Controller
public class AuthorizeController {
    private final GithubProvider githubProvider;
    @Autowired
    public AuthorizeController(GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }

    /**
     * github会自动调用callback请求完成授权登录
     * @param code  code码 登录授权后github会发送
     * @param state 状态码 登录授权后github会发送
     * @return 成功后跳转到主页面
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("dfa64597af0e903657c8");
        accessTokenDTO.setClient_secret("5a437a7b4f300fa4f07b71a5e0cc021180f48377");
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = githubProvider.getUser(token);
        System.out.println(user.getName());
        return "index";
    }
}
