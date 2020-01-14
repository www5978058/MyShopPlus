package com.wzh.myshop.plus.provider.admin.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdmin;
import com.wzh.myshop.plus.provider.admin.api.serivce.UmsAdminService;
import com.wzh.myshop.plus.provider.admin.mapper.UmsAdminMapper;
import com.wzh.myshop.plus.provider.admin.service.fallback.UmsAdminFallback;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wzh
 * @date 2019/11/14 - 14:32
 */
@Service(version = "1.0.0")
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    UmsAdminMapper umsAdminMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public int insert(UmsAdmin umsAdmin) {
        umsAdmin.setPassword(bCryptPasswordEncoder.encode(umsAdmin.getPassword()));
        return umsAdminMapper.insertSelective(umsAdmin);
    }

    @Override
    @SentinelResource(value = "checkUsernameUnique",fallback = "checkUsernameUniqueFallback",fallbackClass = UmsAdminFallback.class)
    public UmsAdmin checkUsernameUnique(String username) {
        return umsAdminMapper.selectOne(new UmsAdmin().setUsername(username));
    }
}
