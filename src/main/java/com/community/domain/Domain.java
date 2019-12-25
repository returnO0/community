package com.community.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-24 14:22
 **/
@Data
public class Domain {
    @TableId(type = IdType.AUTO)
    protected Long id;
    @TableField(value = "create_time")
    protected Object createTime;
    @TableField(value = "update_time")
    protected Object updateTime;
}
