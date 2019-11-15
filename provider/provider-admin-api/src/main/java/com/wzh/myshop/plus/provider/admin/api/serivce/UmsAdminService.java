package com.wzh.myshop.plus.provider.admin.api.serivce;

import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdmin;

/**
 * 用户管理服务
 * @author wzh
 * @date 2019/11/14 - 14:26
 */
public interface UmsAdminService {
    /**
     * 增加用户
     * @param umsAdmin {@link UmsAdmin}
     * @return >0表示新增成功
     */
    int insert(UmsAdmin umsAdmin);

    /**
     * 检查用户名是否唯一
     * @param username 用户名
     * @return {@link UmsAdmin}
     */
    UmsAdmin checkUsernameUnique(String username);
}
