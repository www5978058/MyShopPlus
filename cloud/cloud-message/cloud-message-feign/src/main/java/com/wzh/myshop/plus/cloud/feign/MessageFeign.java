package com.wzh.myshop.plus.cloud.feign;

import com.wzh.myshop.plus.cloud.feign.fallback.MessageFeignFallback;
import com.wzh.myshop.plus.config.interceptor.FeignRequestConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wzh
 * @date 2020/1/10 - 16:51
 */
@FeignClient(value = "cloud-message",path = "message",configuration = FeignRequestConfig.class,fallback = MessageFeignFallback.class)
public interface MessageFeign {

}
