package com.community.provider;

import com.alibaba.fastjson.JSON;
import com.community.dto.AccessTokenDTO;
import com.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * 提供github授权等的相关支持
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-19 15:28
 **/

@Component
public class GithubProvider {
    /**
     * 向指定地址发送get/post请求获取登录的token
     * @param accessTokenDTO    获取token所需参数的封装类
     * @return  获取到的session
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        //okHttp官方固定语法
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                //github提供的获取token地址
                .url("https://github.com/login/oauth/access_token")
                //发送post请求
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            //access_token=856c34e4a680b4be1080c3874b67358269a20bfc&scope=user&token_type=bearer
            //获取到的如上字符串,先根据=号分割,再根据&分割
            return string.split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取登录成功的用户信息
     * @param accessToken token值
     * @return  用户信息
     */
    public GitHubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                //github提供的获取user地址
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            return JSON.parseObject(string, GitHubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
