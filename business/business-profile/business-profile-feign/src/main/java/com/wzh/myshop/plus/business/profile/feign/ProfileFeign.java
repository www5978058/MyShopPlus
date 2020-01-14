package com.wzh.myshop.plus.business.profile.feign;

import com.wzh.myshop.plus.business.profile.feign.fallback.ProfileFeignFallBack;
import com.wzh.myshop.plus.config.interceptor.FeignRequestConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wzh
 * @date 2019/12/27 - 13:44
 */
@FeignClient(value = "business-profile",path = "profile",configuration = FeignRequestConfig.class,fallback = ProfileFeignFallBack.class)
public interface ProfileFeign {
    @GetMapping(value = "info/{username}")
    String info(@PathVariable String username);
}
