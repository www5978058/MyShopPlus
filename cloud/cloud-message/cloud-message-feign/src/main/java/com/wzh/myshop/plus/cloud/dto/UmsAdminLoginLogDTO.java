package com.wzh.myshop.plus.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wzh
 * @date 2020/1/10 - 16:50
 */
@Data
public class UmsAdminLoginLogDTO implements Serializable {

    private static final long serialVersionUID = -8301571393749918190L;
    private Long id;
    private Long adminId;
    private LocalDateTime createTime;
    private String ip;
    private String address;
    private String userAgent;

}
