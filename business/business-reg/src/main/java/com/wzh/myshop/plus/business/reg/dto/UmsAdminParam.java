package com.wzh.myshop.plus.business.reg.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author wzh
 * @date 2019/11/27 - 17:00
 */
@Data
@Accessors(chain = true)
public class UmsAdminParam {
    @NotNull
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",message = "必须为手机号")
    private String username;
    @NotNull
    private String password;
    private String icon;
    private String email;
    private String nickName;
    private String note;
}
