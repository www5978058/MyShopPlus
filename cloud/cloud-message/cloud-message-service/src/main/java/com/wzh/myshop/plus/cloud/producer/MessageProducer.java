package com.wzh.myshop.plus.cloud.producer;

import com.wzh.myshop.plus.cloud.message.MessageSource;
import com.wzh.myshop.plus.cloud.message.api.MessageService;
import com.wzh.myshop.plus.cloud.message.dto.UmsAdminLoginLogDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 * @author wzh
 * @date 2020/1/13 - 13:21
 */
@Component
@Service(version = "1.0.0")
public class MessageProducer implements MessageService {
    @Autowired
    MessageSource messageSource;
    @Override
    public boolean sendAdminLoginLog(UmsAdminLoginLogDTO umsAdminLoginLogDTO) {
        return messageSource.adminLoginLog().send(MessageBuilder.withPayload(umsAdminLoginLogDTO).build());
    }
}
