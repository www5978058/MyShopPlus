package com.wzh.myshop.plus.business.oauth2.controller;

import com.google.common.collect.Maps;
import com.wzh.myshop.plus.business.oauth2.dto.param.LoginParam;
import com.wzh.myshop.plus.business.profile.feign.ProfileFeign;
import com.wzh.myshop.plus.business.profile.feign.dto.UmsAdminDTO;
import com.wzh.myshop.plus.cloud.message.api.MessageService;
import com.wzh.myshop.plus.cloud.message.dto.UmsAdminLoginLogDTO;
import com.wzh.myshop.plus.business.BusinessException;
import com.wzh.myshop.plus.business.BusinessStatus;
import com.wzh.myshop.plus.commons.dto.CodeMessage;
import com.wzh.myshop.plus.commons.dto.ResponseResult;
import com.wzh.myshop.plus.commons.utils.MapperUtils;
import com.wzh.myshop.plus.commons.utils.OkHttpClientUtil;
import com.wzh.myshop.plus.commons.utils.UserAgentUtils;
import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdmin;
import com.wzh.myshop.plus.provider.admin.api.serivce.UmsAdminService;
import eu.bitwalker.useragentutils.Browser;
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
import java.util.Date;
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
    @Reference(version = "1.0.0")
    MessageService messageService;

    @PostMapping("/user/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParam loginParam,HttpServletRequest request){
        // 验证账号密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(),userDetails.getPassword())) {
            throw new BusinessException(BusinessStatus.ADMIN_PASSWORD);
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
        // 发送登录日志
        sendAdminLoginLog(userDetails.getUsername(), request);
        return new ResponseResult<Map<String,Object>>(CodeMessage.LOGIN_SUCCESS.getCode(),CodeMessage.LOGIN_SUCCESS.getMessage(),data);
    }

    @GetMapping("/user/info")
    @PreAuthorize("hasRole('USER')")
    public ResponseResult info() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String info = profileFeign.info(authentication.getName());
        UmsAdminDTO umsAdminDTO = MapperUtils.json2pojoByTree(info, "data", UmsAdminDTO.class);
        if(umsAdminDTO == null || umsAdminDTO.getUsername() == null){
            return MapperUtils.json2pojo(info,ResponseResult.class);
        }
        return new ResponseResult<>(CodeMessage.LOGIN_SUCCESS.getCode(),"获取信息成功",umsAdminDTO);
    }

    @PostMapping("/user/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseResult<Void> logout(HttpServletRequest request){
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<>(CodeMessage.LOGIN_SUCCESS.getCode(),"退出成功");
    }

    /**
     * 发送登录日志
     *
     * @param request {@link HttpServletRequest}
     */
    private void sendAdminLoginLog(String username, HttpServletRequest request) {
        UmsAdmin umsAdmin = umsAdminService.checkUsernameUnique(username);

        if (umsAdmin != null) {
            // 获取请求的用户代理信息
            Browser browser = UserAgentUtils.getBrowser(request);
            String ip = UserAgentUtils.getIpAddr(request);
            String address = UserAgentUtils.getIpInfo(ip).getCity();

            UmsAdminLoginLogDTO dto = new UmsAdminLoginLogDTO();
            dto.setAdminId(umsAdmin.getId());
            dto.setCreateTime(new Date());
            dto.setIp(ip);
            dto.setAddress(address);
            dto.setUserAgent(browser.getName());

            messageService.sendAdminLoginLog(dto);
        }
    }
}
