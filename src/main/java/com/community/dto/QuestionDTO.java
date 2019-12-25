package com.community.dto;

import com.community.domain.User;
import lombok.Data;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-24 20:48
 **/
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long creator;
    private String tag;
    private Integer commentCount;
    private Integer viewConut;
    private Integer likeCount;
    private Object createTime;
    private Object updateTime;
    private User user;
}
