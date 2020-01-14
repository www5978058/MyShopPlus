package com.wzh.myshop.plus.business.oauth2.dto.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 登录信息
 * @author wzh
 * @date 2019/11/15 - 17:31
 */
@Data
@Accessors(chain = true)
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1276490910821899760L;
    private String name;
    private String avatar;
}
