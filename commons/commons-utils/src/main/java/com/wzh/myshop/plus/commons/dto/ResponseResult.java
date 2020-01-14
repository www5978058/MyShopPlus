package com.wzh.myshop.plus.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的返回格式
 * @author wzh
 * @date 2019/11/14 - 15:00
 */
@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 3468352004150968551L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回对象
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer code) {
        super();
        this.code = code;
    }

    public ResponseResult(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, Throwable throwable) {
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ResponseResult(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
