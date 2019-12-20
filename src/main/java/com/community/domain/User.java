package com.community.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * @description (USER)表实体类
 * @author zhiqiang.hu01@hand-china.com
 * @date 2019-12-20 10:52:13
 */
 
@TableName(value = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @TableField(value = "account_id")
    private Long accountId;

    @TableField(value = "token")
    private String token;

    @TableField(value = "name")
    private String name;
    
    @TableField(value = "create_time")
    private Object createTime;
    
    @TableField(value = "update_time")
    private Object updateTime;
}