package com.wzh.myshop.plus.business.profile.feign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wzh
 * @date 2020/1/2 - 15:56
 */
@Data
public class UmsAdminDTO implements Serializable {
    private static final long serialVersionUID = 2251050087429498193L;
    private Long id;
    private String username;
    /**
     * 头像
     */
    private String icon;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 备注信息
     */
    private String note;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;
}
