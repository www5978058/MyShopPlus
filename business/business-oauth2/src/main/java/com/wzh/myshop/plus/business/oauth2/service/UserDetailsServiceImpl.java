package com.wzh.myshop.plus.business.oauth2.service;

import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdmin;
import com.wzh.myshop.plus.provider.admin.api.serivce.UmsAdminService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义用户认证与授权
 *
 * @author wzh
 * @date 2019/11/14 - 15:53
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    UmsAdminService umsAdminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UmsAdmin umsAdmin = umsAdminService.checkUsernameUnique(s);
        if (umsAdmin == null) {
            return null;
        }
        return User.withUsername(s).password(umsAdmin.getPassword()).roles("USER").build();
    }
}
