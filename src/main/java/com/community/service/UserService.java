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
    /**
     * 将用户对象插入到数据库
     * @param entity 用户信息
     * @return 返回插入是否成功
     */
    @Override
    public boolean insert(User entity) {
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        return super.insert(entity);
    }

    /**
     * 判断用户是否存在
     * @param gitHubUser github用户
     * @return  为空为false 不为空为true
     */
    public boolean isExist(GitHubUser gitHubUser) {
        Wrapper<User> wrapper=new EntityWrapper<User>().eq("account_id",gitHubUser.getId());
        User user = selectOne(wrapper);
        return !StringUtils.isEmpty(user);
    }

    /**
     * 根据AccountId更新用户信息
     * @param user 用户对象
     * @return  是否更新成功
     */
    public Boolean updateByAccountId(User user) {
        Wrapper<User> wrapper=new EntityWrapper<User>().eq("account_id",user.getAccountId());
        user.setUpdateTime(new Date());
        return update(user, wrapper);
    }


}