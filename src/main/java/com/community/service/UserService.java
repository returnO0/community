package com.community.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.community.domain.User;
import com.community.dto.GitHubUser;
import com.community.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * (User)表服务接口
 *
 * @author zhiqiang.hu01@hand-china.com
 * @since 2019-12-20 10:52:14
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean insert(User entity) {
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        return super.insert(entity);
    }

    public boolean isExist(GitHubUser gitHubUser) {
        Wrapper<User> wrapper=new EntityWrapper<User>().eq("account_id",gitHubUser.getId());
        User user = selectOne(wrapper);
        return !StringUtils.isEmpty(user);
    }
}