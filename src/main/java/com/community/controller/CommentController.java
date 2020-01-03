package com.community.controller;

import com.community.domain.User;
import com.community.dto.CommentDTO;
import com.community.dto.ResultDTO;
import com.community.exception.CustomizeErrorCode;
import com.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


/**
 * (Comment)表控制层
 *
 * @author zhaowei.zhang01@hand-china.com
 * @since 2020-01-02 15:38:39
 */
@Controller
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 插入评论
     * @param commentDTO 评论dto
     * @return comment对象以及状态码
     */
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResultDTO insertComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        //返回实体类和状态码
        Object user = request.getSession().getAttribute("user");
        if (!StringUtils.isEmpty(user)&&user instanceof User){
            commentService.insertComment(commentDTO, (User) user);
            return ResultDTO.okOf();
        }else {
            return ResultDTO.messageOf(CustomizeErrorCode.NO_LOGIN);
        }
    }
}