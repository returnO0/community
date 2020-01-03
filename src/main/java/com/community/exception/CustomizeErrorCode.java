package com.community.exception;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2019-12-26 16:34
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    /**
     * 自定义异常
     */
    QUESTION_NOT_FOUND(2001,"你找的问题不存在,要不换一个试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    TYPE_PARAM_WRONG(2003,"回复类型错误"),
    COMMENT_NOT_FOUND(2004,"评论没找到"),
    INSERT_DEFAULT(2005,"插入失败,数据库错误"),
    NO_LOGIN(2003,"未登录,不能进行评论");
    private Integer code;
    private String message;
    CustomizeErrorCode(Integer code,String message) {
        this.code=code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
