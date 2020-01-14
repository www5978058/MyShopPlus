package com.wzh.myshop.plus.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wzh
 * @date 2020/1/2 - 10:32
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudUploadApplication.class,args);
    }
}
