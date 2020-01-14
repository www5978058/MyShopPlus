package com.wzh.myshop.plus.cloud.message.api;

import com.wzh.myshop.plus.cloud.message.dto.UmsAdminLoginLogDTO;

/**
 * @author wzh
 * @date 2020/1/13 - 13:06
 */
public interface MessageService {
    boolean sendAdminLoginLog(UmsAdminLoginLogDTO umsAdminLoginLogDTO);
}
