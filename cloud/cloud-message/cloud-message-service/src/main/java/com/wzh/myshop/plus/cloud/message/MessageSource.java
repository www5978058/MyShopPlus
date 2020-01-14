package com.wzh.myshop.plus.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author wzh
 * @date 2020/1/13 - 8:25
 */
public interface MessageSource {
    @Output("admin-login-log-topic")
    MessageChannel adminLoginLog();
}
