package com.community.advice;

import com.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @version 1.0
 * @date 2019-12-26 15:48
 **/
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable throwable, Model model){
        HttpStatus status=getStatus(request);
        if (throwable instanceof CustomizeException){
            model.addAttribute("message",throwable.getMessage());
            model.addAttribute("status",status.value());
        }else {
            model.addAttribute("message","服务冒烟了,要不然稍等下再来试试!!!");
            model.addAttribute("status",status.value());
        }
        return new ModelAndView("error");
    }
    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode= (Integer) request.getAttribute("javax.servlet.error.status_dode");
        if (StringUtils.isEmpty(statusCode)){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
