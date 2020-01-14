package com.wzh.myshop.plus.config.interceptor;


import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * Feign拦截器 携带参数和请求头请求其他服务
 * @author wzh
 * @date 2019/12/27 - 15:02
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while(headerNames.hasMoreElements()){
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name, value);
            }
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder sb = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()){
                String param = parameterNames.nextElement();
                String value = request.getParameter(param);
                if("access_token".equals(param)){
                    requestTemplate.header("authorization","Bearer "+value);
                }else{
                    sb.append(param).append("=").append(value).append("&");
                }
            }
        }
        if (sb.length()>0) {
            sb.deleteCharAt(sb.length()-1);
            requestTemplate.body(Request.Body.bodyTemplate(sb.toString(), Charset.defaultCharset()));
        }
    }
}
