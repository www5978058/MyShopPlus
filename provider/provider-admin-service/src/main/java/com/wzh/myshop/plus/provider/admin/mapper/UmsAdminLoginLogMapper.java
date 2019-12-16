package com.wzh.myshop.plus.provider.admin.mapper;

import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdminLoginLog;
import tk.mybatis.mapper.MyMapper;

/**
@author wzh
@date 2019/11/20 - 9:04
*/
public interface UmsAdminLoginLogMapper extends MyMapper<UmsAdminLoginLog> {
    UmsAdminLoginLog selectByPrimaryAndLock(Integer id);
}
