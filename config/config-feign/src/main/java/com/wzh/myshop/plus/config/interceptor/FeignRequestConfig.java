package com.wzh.myshop.plus.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wzh
 * @date 2019/12/27 - 15:18
 */
@Configuration
public class FeignRequestConfig {
    @Bean
    public FeignRequestInterceptor feignRequestInterceptor(){
        return new FeignRequestInterceptor();
    }
}
