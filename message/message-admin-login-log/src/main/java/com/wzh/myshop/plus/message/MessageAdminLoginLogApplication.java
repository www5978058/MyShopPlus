package com.wzh.myshop.plus.message;

import com.wzh.myshop.plus.message.config.AdminLoginLogSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author wzh
 * @date 2020/1/13 - 14:39
 */
@SpringBootApplication
@EnableBinding({AdminLoginLogSink.class})
public class MessageAdminLoginLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageAdminLoginLogApplication.class, args);
    }

}
