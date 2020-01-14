package com.wzh.myshop.plus.business.oauth2.dto.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录参数
 * @author wzh
 * @date 2019/11/15 - 10:37
 */
@Data
public class LoginParam implements Serializable {
    private static final long serialVersionUID = -53612472199683575L;
    private String username;
    private String password;
    @DateTimeFormat(pattern = "yyyy")
    private Date time;
}
