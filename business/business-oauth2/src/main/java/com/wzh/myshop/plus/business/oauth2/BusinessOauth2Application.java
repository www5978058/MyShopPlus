package com.wzh.myshop.plus.business.oauth2;

import com.wzh.myshop.plus.business.profile.feign.ProfileFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * EnableFeign Clients扫描会覆盖@SpringBootApplication扫描的，导致注入失败，需要clients指定
 * @author wzh
 * @date 2019/11/14 - 15:45
 */
@EnableDiscoveryClient
@EnableFeignClients(clients = {ProfileFeign.class})
@SpringBootApplication(scanBasePackages = "com.wzh.myshop.plus.business")
public class BusinessOauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(BusinessOauth2Application.class,args);
    }
}
