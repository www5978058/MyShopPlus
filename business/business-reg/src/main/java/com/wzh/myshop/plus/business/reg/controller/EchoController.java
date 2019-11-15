package com.wzh.myshop.plus.business.reg.controller;

import com.wzh.myshop.plus.provider.admin.api.serivce.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzh
 * @date 2019/11/14 - 14:15
 */
@RestController
@RequestMapping("echo")
public class EchoController {

    @Reference(version = "1.0.0")
    EchoService echoService;

    @GetMapping("{message}")
    public String echo(@PathVariable String message){
        return echoService.echo(message);
    }
}
