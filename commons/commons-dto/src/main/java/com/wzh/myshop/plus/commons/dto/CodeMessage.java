package com.wzh.myshop.plus.commons.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * 响应状态和内容
 * @author wzh
 * @date 2019/11/15 - 17:09
 */
public enum CodeMessage {
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(20000,"登录成功"),
    /**
     * 失败
     */
    FAIL(50000,"失败"),
    /**
     * 非法token
     */
    ILLEGAL_TOKEN(50008,"非法token"),
    /**
     * token过期
     */
    TOKEN_EXPIRED(50014,"token过期");

    private Integer code;
    private String message;

    CodeMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    /** 通过value获取configKey */
    public static String getMessageByCoide(String code){
        for (CodeMessage t : CodeMessage.values()) {
            if(StringUtils.isNotBlank(code) && t.code.equals(code)){
                return t.message;
            }
        }
        return "";
    }

}
