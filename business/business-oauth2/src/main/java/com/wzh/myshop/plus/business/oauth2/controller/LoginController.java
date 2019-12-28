package com.wzh.myshop.plus.business.oauth2.controller;

import com.google.common.collect.Maps;
import com.wzh.myshop.plus.business.oauth2.dto.LoginInfo;
import com.wzh.myshop.plus.business.oauth2.dto.LoginParam;
import com.wzh.myshop.plus.business.profile.feign.ProfileFeign;
import com.wzh.myshop.plus.commons.dto.CodeMessage;
import com.wzh.myshop.plus.commons.dto.ResponseResult;
import com.wzh.myshop.plus.commons.utils.MapperUtils;
import com.wzh.myshop.plus.commons.utils.OkHttpClientUtil;
import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdmin;
import com.wzh.myshop.plus.provider.admin.api.serivce.UmsAdminService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * 登录管理
 * @author wzh
 * @date 2019/11/15 - 10:36
 */
@RestController
public class LoginController {
    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";
    @Value("${business.oauth2.grant_type}")
    String oauthGrantType;
    @Value("${business.oauth2.client_id}")
    String oauthClientId;
    @Value("${business.oauth2.client_secret}")
    String oauthClientSecret;
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    TokenStore tokenStore;
    @Autowired
    ProfileFeign profileFeign;
    @Reference(version = "1.0.0")
    UmsAdminService umsAdminService;

    @PostMapping("/user/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParam loginParam){
        // 验证账号密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(),userDetails.getPassword())) {
            return new ResponseResult<>(CodeMessage.FAIL.getCode(),CodeMessage.FAIL.getMessage());
        }
        Map<String,Object> data = Maps.newHashMap();
        Map<String,String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauthGrantType);
        params.put("client_id", oauthClientId);
        params.put("client_secret", oauthClientSecret);
        try {
            String json = Objects.requireNonNull(OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params).body()).string();
            data.put("token",MapperUtils.json2map(json).get("access_token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult<Map<String,Object>>(CodeMessage.LOGIN_SUCCESS.getCode(),CodeMessage.LOGIN_SUCCESS.getMessage(),data);
    }

    @GetMapping("/user/info")
    @PreAuthorize("hasRole('USER')")
    public ResponseResult<LoginInfo> info() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String info = profileFeign.info(authentication.getName());
        UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(info, "data", UmsAdmin.class);
        LoginInfo loginInfo = new LoginInfo().setName(umsAdmin.getUsername()).setAvatar(umsAdmin.getIcon());
        return new ResponseResult<>(CodeMessage.LOGIN_SUCCESS.getCode(),"获取信息成功",loginInfo);
    }

    @PostMapping("/user/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseResult<Void> logout(HttpServletRequest request){
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<>(CodeMessage.LOGIN_SUCCESS.getCode(),"退出成功");
    }
}
