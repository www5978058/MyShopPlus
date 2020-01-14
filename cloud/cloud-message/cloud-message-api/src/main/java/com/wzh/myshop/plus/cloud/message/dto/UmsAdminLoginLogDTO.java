package com.wzh.myshop.plus.cloud.message.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wzh
 * @date 2020/1/13 - 13:06
 */
@Data
public class UmsAdminLoginLogDTO implements Serializable {

    private static final long serialVersionUID = -8301571393749918190L;
    private Long id;
    private Long adminId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String ip;
    private String address;
    private String userAgent;
}
