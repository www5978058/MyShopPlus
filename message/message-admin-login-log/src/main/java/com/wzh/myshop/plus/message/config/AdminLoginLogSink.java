package com.wzh.myshop.plus.message.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wzh
 * @date 2020/1/13 - 14:40
 */
public interface AdminLoginLogSink {
    @Input("admin-login-log-topic")
    SubscribableChannel adminLoginLog();
}
