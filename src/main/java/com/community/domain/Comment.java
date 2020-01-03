package com.community.domain;

import lombok.*;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 *
 * @description (comment)表实体类
 * @author zhaowei.zhang01@hand-china.com
 * @date 2020-01-02 15:38:37
 */
 
@EqualsAndHashCode(callSuper = true)
@TableName(value = "comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Domain {
    /**
      父评论id 
    */
    @TableField(value = "parent_id")
    private Long parentId;
    
    /**
      评论类型(几级评论) 
    */
    @TableField(value = "type")
    private Integer type;
    
    /**
      评论人 
    */
    @TableField(value = "commentator")
    private Long commentator;
    
    /**
      点赞数量 
    */
    @TableField(value = "like_count")
    private Integer likeCount;

    /**
     * 回复内容
     */
    @TableField(value = "content")
    private String content;
}