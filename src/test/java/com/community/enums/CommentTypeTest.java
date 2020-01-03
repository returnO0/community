package com.community.enums;

import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2020-01-02 18:54
 **/
class CommentTypeTest {
    CommentType commentType;

    @Test
    void getDesc() {
        Serializable value = CommentType.COMMENT.getValue();
        System.out.println(value);
    }

    @Test
    void getValue() {
        CommentType parse = CommentType.parse(2);
        System.out.println(parse);
    }
}