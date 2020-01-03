package com.community.dto;

import com.community.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2020-01-02 19:41
 **/
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO messageOf(Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO messageOf(CustomizeErrorCode customizeErrorCode){
        return messageOf(customizeErrorCode.getCode(),customizeErrorCode.getMessage());
    }

    public static ResultDTO okOf(){
        return messageOf(200,"请求成功");
    }

}
