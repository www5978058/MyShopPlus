package com.wzh.myshop.plus.cloud.controller;

import com.wzh.myshop.plus.cloud.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzh
 * @date 2020/1/13 - 8:16
 */
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageProducer messageProducer;
}
