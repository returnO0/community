package com.community.controller;

import com.community.domain.Comment;
import com.community.domain.User;
import com.community.dto.CommentDTO;
import com.community.dto.ResultDTO;
import com.community.enums.CommentType;
import com.community.exception.CustomizeErrorCode;
import com.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @param comment 回复对象
     * @return comment对象以及状态码
     */
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResultDTO insertComment(@RequestBody Comment comment, HttpServletRequest request){
        //返回实体类和状态码
        Object user = request.getSession().getAttribute("user");
        if (!StringUtils.isEmpty(user)&&user instanceof User){
            commentService.insertComment(comment, (User) user);
            return ResultDTO.okOf();
        }else {
            return ResultDTO.messageOf(CustomizeErrorCode.NO_LOGIN);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comment(@PathVariable(name = "id")Long id){
        List<CommentDTO> comments=commentService.selectListById(id, CommentType.COMMENT);
        return ResultDTO.okOf(comments);
    }
}