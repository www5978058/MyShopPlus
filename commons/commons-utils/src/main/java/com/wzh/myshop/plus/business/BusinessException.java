package com.wzh.myshop.plus.business;

import lombok.Data;

/**
 * 全局业务异常
 * <p>
 * Description:
 * </p>
 *
 * @author Lusifer
 * @version v1.0.0
 * @date 2019-10-24 01:21:25
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 3034121940056795549L;

    private Integer code;

    private BusinessException() {

    }

    public BusinessException(BusinessStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
    }
}
