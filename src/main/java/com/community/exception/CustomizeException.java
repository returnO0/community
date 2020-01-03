package com.community.exception;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2019-12-26 16:14
 **/
public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }
    public Integer getCode(){
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
