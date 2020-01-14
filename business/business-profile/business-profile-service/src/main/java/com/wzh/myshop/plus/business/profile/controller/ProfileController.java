package com.wzh.myshop.plus.business.profile.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wzh.myshop.plus.business.profile.feign.dto.UmsAdminDTO;
import com.wzh.myshop.plus.business.profile.feign.fallback.ProfileFeignFallBack;
import com.wzh.myshop.plus.commons.dto.CodeMessage;
import com.wzh.myshop.plus.commons.dto.ResponseResult;
import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdmin;
import com.wzh.myshop.plus.provider.admin.api.serivce.UmsAdminService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人信息管理
 * @author wzh
 * @date 2019/12/27 - 13:39
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {
    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @GetMapping(value = "info/{username}")
    @SentinelResource(value = "info",fallback = "infoFallBack",fallbackClass = ProfileFeignFallBack.class)
    public ResponseResult<UmsAdminDTO> info(@PathVariable String username){
        UmsAdmin umsAdmin = umsAdminService.checkUsernameUnique(username);
        UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
        BeanUtils.copyProperties(umsAdmin,umsAdminDTO);
        return new ResponseResult(CodeMessage.LOGIN_SUCCESS.getCode(),"查询个人信息",umsAdminDTO);
    }
}
