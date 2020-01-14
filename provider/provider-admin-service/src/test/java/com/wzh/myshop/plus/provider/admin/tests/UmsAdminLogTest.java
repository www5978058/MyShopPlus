package com.wzh.myshop.plus.provider.admin.tests;

import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdminLoginLog;
import com.wzh.myshop.plus.provider.admin.mapper.UmsAdminLoginLogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author wzh
 * @date 2019/11/19 - 15:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsAdminLogTest {
    @Autowired
    UmsAdminLoginLogMapper umsAdminLoginLogMapper;
    @Test
    public void test(){
        UmsAdminLoginLog umsAdminLoginLog = new UmsAdminLoginLog();
        umsAdminLoginLog.setAdminId(3L);
        umsAdminLoginLog.setCreateTime(new Date());
        umsAdminLoginLog.setIp("0:0:0:0:0:0:0:1");
        System.out.println(umsAdminLoginLog);
        umsAdminLoginLogMapper.insertSelective(umsAdminLoginLog);
        System.out.println(umsAdminLoginLog);
    }
}
