package com.wzh.myshop.plus.cloud;

import com.wzh.myshop.plus.cloud.message.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author wzh
 * @date 2020/1/10 - 17:15
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(MessageSource.class)
public class CloudMessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudMessageApplication.class,args);
    }
}
