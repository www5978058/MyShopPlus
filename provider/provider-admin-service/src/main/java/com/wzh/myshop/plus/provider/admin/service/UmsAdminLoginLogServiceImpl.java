package com.wzh.myshop.plus.provider.admin.service;

import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdminLoginLog;
import com.wzh.myshop.plus.provider.admin.api.serivce.UmsAdminLoginLogService;
import com.wzh.myshop.plus.provider.admin.mapper.UmsAdminLoginLogMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wzh
 * @date 2020/1/10 - 14:29
 */
@Service(version = "1.0.0")
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService {

    @Autowired
    UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public int insert(UmsAdminLoginLog umsAdminLoginLog) {
        return umsAdminLoginLogMapper.insertSelective(umsAdminLoginLog);
    }
}
