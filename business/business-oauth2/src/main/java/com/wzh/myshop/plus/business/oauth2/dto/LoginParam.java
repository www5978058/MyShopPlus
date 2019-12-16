package com.wzh.myshop.plus.business.oauth2.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 * @author wzh
 * @date 2019/11/15 - 10:37
 */
@Data
public class LoginParam implements Serializable {
    private String username;
    private String password;
}
