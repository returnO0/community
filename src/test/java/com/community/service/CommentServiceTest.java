package com.community.service;

import com.community.dto.CommentDTO;
import com.community.enums.CommentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2020-01-07 09:41
 **/
@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Test
    void selectListByQuestionId() {
        Long aLong = Long.valueOf("28");
        List<CommentDTO> commentDTOS = commentService.selectListById(aLong, CommentType.COMMENT);
        System.out.println(commentDTOS);
    }
}