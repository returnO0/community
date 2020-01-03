package com.community.domain;

import lombok.*;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * @description (question)表实体类
 * @author zhaowei.zhang01@hand-china.com
 * @date 2019-12-24 14:29:28
 */

@EqualsAndHashCode(callSuper = true)
@TableName(value = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question extends Domain {
    @TableField(value = "title")
    private String title;
    
    @TableField(value = "description")
    private String description;
    
    @TableField(value = "creator")
    private Long creator;
    
    @TableField(value = "comment_count")
    private Integer commentCount;

    @TableField(value = "view_count")
    private Integer viewCount;
    
    @TableField(value = "like_count")
    private Integer likeCount;
    
    @TableField(value = "tag")
    private String tag;

}