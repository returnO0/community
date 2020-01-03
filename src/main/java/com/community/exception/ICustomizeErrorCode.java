package com.community.exception;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2019-12-26 16:32
 **/
public interface ICustomizeErrorCode {
    /**
     *获取错误码
     * @return 错误码
     */
    Integer getCode();

    /**
     *获取错误信息
     * @return 错误信息
     */
    String getMessage();
}
