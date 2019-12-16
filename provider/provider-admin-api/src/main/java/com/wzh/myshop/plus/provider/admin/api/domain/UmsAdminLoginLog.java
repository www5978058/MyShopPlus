package com.wzh.myshop.plus.provider.admin.api.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
@author wzh
@date 2019/11/20 - 9:04
*/
@Data
@Table(name = "ums_admin_login_log")
public class UmsAdminLoginLog implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "ip")
    private String ip;

    @Column(name = "address")
    private String address;

    /**
     * 浏览器登录类型
     */
    @Column(name = "user_agent")
    private String userAgent;

    private static final long serialVersionUID = 1L;
}