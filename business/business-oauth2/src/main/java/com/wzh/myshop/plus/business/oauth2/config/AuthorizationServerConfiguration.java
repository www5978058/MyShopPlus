package com.wzh.myshop.plus.business.oauth2.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author wzh
 * @date 2019/11/14 - 15:50
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    HikariDataSource hikariDataSource;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    //令牌生成方案  默认在内存中生成普通令牌
    @Bean
    public TokenStore tokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    public ClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(hikariDataSource);
    }

    // 配置客户端详情信息服务 客户端通过client_id和client_secret来访问资源
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

    // 令牌访问服务
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(jdbcClientDetailsService());//客户端信息服务
        tokenServices.setTokenStore(tokenStore());// 令牌生成方案
        tokenServices.setRefreshTokenValiditySeconds(7200);//令牌默认有效期2小时
        tokenServices.setAccessTokenValiditySeconds(259200);// 刷新令牌默认有效期3天
        tokenServices.setSupportRefreshToken(true);// 设置刷新令牌
        return tokenServices;
    }

    // 授权码模式
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new JdbcAuthorizationCodeServices(hikariDataSource);
    }

    // 令牌访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)//密码模式需要
                .authorizationCodeServices(authorizationCodeServices())//授权码模式需要
                .tokenStore(tokenStore())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//允许POST提交
    }

    // 令牌访问策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")     //oauth/token_key公开
                .checkTokenAccess("permitAll()")  //oauth/check_token公开
                .allowFormAuthenticationForClients();//允许表单认证，申请令牌
    }
}
