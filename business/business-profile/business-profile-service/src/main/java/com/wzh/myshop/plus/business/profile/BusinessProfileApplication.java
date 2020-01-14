package com.wzh.myshop.plus.business.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wzh
 * @date 2019/12/27 - 13:36
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class BusinessProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessProfileApplication.class,args);
    }
}
