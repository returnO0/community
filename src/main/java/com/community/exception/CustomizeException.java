package com.community.exception;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2019-12-26 16:14
 **/
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
