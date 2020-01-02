package com.community.exception;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2019-12-26 16:34
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    /**
     * 问题不存在异常
     */
    QUESTION_NOT_FOUND("你找的问题不存在,要不换一个试试");
    private String message;
    CustomizeErrorCode(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
