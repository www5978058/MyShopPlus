package com.wzh.myshop.plus.provider.admin.service.fallback;

import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdmin;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户服务提供者熔断器
 * @author wzh
 * @date 2020/1/9 - 14:56
 */
@Slf4j
public class UmsAdminFallback {

    public static UmsAdmin checkUsernameUniqueFallback(String username,Throwable throwable){
        log.warn("invoke checkUsernameUnique: "+throwable.getClass().getTypeName());
        return null;
    }
}
