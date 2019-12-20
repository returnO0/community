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
    public AuthorizeController(GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String secret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    private UserService userService;
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
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(secret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = githubProvider.getUser(token);
        if(!StringUtils.isEmpty(gitHubUser)&&!userService.isExist(gitHubUser)){
            User user=new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gitHubUser.getName());
            user.setAccountId(gitHubUser.getId());
            userService.insert(user);
            request.getSession().setAttribute("user",gitHubUser);
            return "redirect:/";
        }else{
            request.getSession().setAttribute("user",gitHubUser);
            return "redirect:/";
        }
    }
}
