package com.wzh.myshop.plus.message.consumer;

import com.wzh.myshop.plus.commons.utils.MapperUtils;
import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdminLoginLog;
import com.wzh.myshop.plus.provider.admin.api.serivce.UmsAdminLoginLogService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @author wzh
 * @date 2020/1/13 - 14:49
 */
@Service
public class AdminLoginLogConsumer {
    @Reference(version = "1.0.0")
    UmsAdminLoginLogService umsAdminLoginLogService;

    @StreamListener("admin-login-log-topic")
    public void receiveAdminLoginLog(String umsAdminLoginLogJson) throws Exception {
        UmsAdminLoginLog umsAdminLoginLog = MapperUtils.json2pojo(umsAdminLoginLogJson, UmsAdminLoginLog.class);
        umsAdminLoginLogService.insert(umsAdminLoginLog);
    }
}
