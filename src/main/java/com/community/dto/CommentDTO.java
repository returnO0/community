package com.community.dto;

import lombok.Data;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2020-01-02 15:53
 **/
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
