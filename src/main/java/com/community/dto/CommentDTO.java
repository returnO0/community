package com.community.dto;

import com.community.domain.User;
import lombok.Data;

import java.util.Date;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2020-01-02 15:53
 **/
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private String content;
    private Integer type;
    private Long commentator;
    private Integer likeCount;
    private Date createTime;
    private User user;
}
