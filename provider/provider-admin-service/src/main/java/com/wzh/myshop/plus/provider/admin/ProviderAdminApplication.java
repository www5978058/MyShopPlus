package com.wzh.myshop.plus.provider.admin;

import com.wzh.myshop.plus.config.DubboSentinelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author wzh
 * @date 2019/11/14 - 11:15
 */
@SpringBootApplication(scanBasePackageClasses = {ProviderAdminApplication.class, DubboSentinelConfig.class})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.wzh.myshop.plus.provider.admin.mapper")
public class ProviderAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAdminApplication.class,args);
    }
}
