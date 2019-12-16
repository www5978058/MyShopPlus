package com.wzh.myshop.plus.provider.admin.tests;

import com.wzh.myshop.plus.provider.admin.api.domain.UmsAdminLoginLog;
import com.wzh.myshop.plus.provider.admin.mapper.UmsAdminLoginLogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

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
        umsAdminLoginLog.setCreateTime(LocalDateTime.now());
        umsAdminLoginLog.setIp("0:0:0:0:0:0:0:1");
        System.out.println(umsAdminLoginLog);
        umsAdminLoginLogMapper.insertSelective(umsAdminLoginLog);
        System.out.println(umsAdminLoginLog);
        /*Example example = new Example(UmsAdminLoginLog.class);
        example.createCriteria().andGreaterThan("createTime", LocalDateTime.of(2019,3,1,0,0,0));
        List<UmsAdminLoginLog> umsAdminLoginLogs = umsAdminLoginLogMapper.selectByExample(example);
        umsAdminLoginLogs.forEach(System.out::println);*/
    }
}
