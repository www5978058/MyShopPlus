package com.wzh.myshop.plus.business.reg.controller;

import com.wzh.myshop.plus.commons.dto.ResponseResult;
import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdmin;
import com.wzh.myshop.plus.provider.admin.api.serivce.UmsAdminService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册.
 *
 * @author wzh
 * @date 2019/11/14 - 14:49
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "reg")
public class RegController {

    @Reference(version = "1.0.0")
    UmsAdminService umsAdminService;

    /**
     * 注册
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link ResponseResult}
     */
    @PostMapping("")
    public ResponseResult<UmsAdmin> reg(@RequestBody UmsAdmin umsAdmin) {
        String message = validateReg(umsAdmin);
        // 通过验证
        if (message == null) {
            int result = umsAdminService.insert(umsAdmin);
            if (result > 0) {
                return new ResponseResult<UmsAdmin>(HttpStatus.OK.value(), "用户注册成功",umsAdminService.checkUsernameUnique(umsAdmin.getUsername()));
            }
            message = "用户注册失败";
        }
        return new ResponseResult<>(HttpStatus.NOT_ACCEPTABLE.value(), message);
    }

    /**
     * 注册信息验证
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return 验证结果
     */
    private String validateReg(UmsAdmin umsAdmin) {
        UmsAdmin admin = umsAdminService.checkUsernameUnique(umsAdmin.getUsername());
        return admin == null ? null : "用户名已存在";
    }
}
