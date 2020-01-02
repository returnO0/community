package com.community.controller;

import com.community.domain.User;
import com.community.dto.AccessTokenDTO;
import com.community.dto.GitHubUser;
import com.community.provider.GithubProvider;
import com.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-19 15:18
 **/
@Controller
public class AuthorizeController {
    private final GithubProvider githubProvider;
    @Autowired
    public AuthorizeController(GithubProvider githubProvider, UserService userService) {
        this.githubProvider = githubProvider;
        this.userService = userService;
    }
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String secret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    private final UserService userService;
    /**
     * github会自动调用callback请求完成授权登录
     * @param code  code码 登录授权后github会发送
     * @param state 状态码 登录授权后github会发送
     * @return 成功后跳转到主页面
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        //将GitHub返回的code的state 以及自己的clientId secret redirectUri 存入到对象
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(secret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        //将数据发送给github并拿到返回的token值
        String token = githubProvider.getAccessToken(accessTokenDTO);
        //将token值发给GitHub得到用户信息
        GitHubUser gitHubUser = githubProvider.getUser(token);
        if(!StringUtils.isEmpty(gitHubUser)){
            //将GitHub用户信息存入到user对象中
            User user=new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gitHubUser.getName());
            user.setAccountId(gitHubUser.getId());
            user.setBio(gitHubUser.getBio());
            user.setImgUrl(gitHubUser.getAvatar_url());
            //根据accountId判断用户是否存在,如果存在则更新用户信息
            if (!userService.isExist(gitHubUser)){
                userService.insert(user);
            }else {
                userService.updateByAccountId(user);
            }
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
