package com.wzh.myshop.plus.business.oauth2.tests;

import com.google.common.collect.Maps;
import com.wzh.myshop.plus.commons.utils.MapperUtils;
import com.wzh.myshop.plus.commons.utils.OkHttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author wzh
 * @date 2019/11/15 - 14:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OKHttp3Tests {
    @Test
    public void testGet() {
        String url = "http://www.baidu.com";
        System.out.println(OkHttpClientUtil.getInstance().getData(url));
    }

    @Test
    public void testPost() {
        String url = "http://localhost:9001/oauth/token";
        Map<String,String> map = Maps.newHashMap();
        map.put("username", "admin");
        map.put("password", "123456");
        map.put("grant_type", "password");
        map.put("client_id", "client");
        map.put("client_secret", "secret");
        try {
            String json = OkHttpClientUtil.getInstance().postData(url, map).body().string();
            System.out.println(MapperUtils.json2map(json).get("access_token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
