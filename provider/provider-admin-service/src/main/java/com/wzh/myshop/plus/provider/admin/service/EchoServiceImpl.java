package com.wzh.myshop.plus.provider.admin.service;

import com.wzh.myshop.plus.provider.admin.api.serivce.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author wzh
 * @date 2019/11/14 - 11:18
 */
@Service(version = "1.0.0")
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String message) {
        return "hello dubbo "+message;
    }
}
