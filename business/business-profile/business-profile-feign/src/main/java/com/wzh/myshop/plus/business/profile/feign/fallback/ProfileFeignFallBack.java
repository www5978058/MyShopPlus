package com.wzh.myshop.plus.business.profile.feign.fallback;

import com.wzh.myshop.plus.business.profile.feign.ProfileFeign;
import com.wzh.myshop.plus.business.profile.feign.dto.UmsAdminDTO;
import com.wzh.myshop.plus.commons.dto.ResponseResult;
import com.wzh.myshop.plus.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * @author wzh
 * @date 2019/12/27 - 15:31
 */
@Component
public class ProfileFeignFallBack implements ProfileFeign {
    public static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";
    @Override
    public String info(String username) {
        UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
        try {
            return MapperUtils.obj2json(new ResponseResult<UmsAdminDTO>(20005,BREAKING_MESSAGE,umsAdminDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
