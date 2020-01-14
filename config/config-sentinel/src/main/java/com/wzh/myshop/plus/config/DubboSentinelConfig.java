package com.wzh.myshop.plus.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 仅 Dubbo 服务需要该配置，Spring Cloud Alibaba 无需加载该配置
 * @author wzh
 * @date 2020/1/9 - 10:23
 */
@Configuration
public class DubboSentinelConfig {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
