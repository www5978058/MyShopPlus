package com.wzh.myshop.plus.business.oauth2.service;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义用户认证与授权
 * @author wzh
 * @date 2019/11/14 - 15:53
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String USERNAME = "admin";
    public static final String PASSWORD = "$2a$10$dW3bbws8gafqkJaV6fd56upXPiPm6PSa1jb2kl6Nbqa0ByG3DbSdS";
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s.equals(USERNAME)) {
            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            grantedAuthorities.add(grantedAuthority);
            return new User(USERNAME,PASSWORD,grantedAuthorities);
        }
        return null;
    }
}
