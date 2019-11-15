package com.wzh.myshop.plus.provider.admin.tests;

import com.wzh.myshop.plus.provider.admin.mapper.UmsAdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wzh
 * @date 2019/11/14 - 13:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsAdminTest {

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Test
    public void test(){
        System.out.println(umsAdminMapper.selectByPrimaryKey(1));
    }
}
