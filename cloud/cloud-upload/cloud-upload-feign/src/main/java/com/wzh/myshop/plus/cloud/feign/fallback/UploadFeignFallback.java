package com.wzh.myshop.plus.cloud.feign.fallback;

import com.wzh.myshop.plus.cloud.feign.UploadFeign;
import com.wzh.myshop.plus.commons.dto.CodeMessage;
import com.wzh.myshop.plus.commons.dto.ResponseResult;
import com.wzh.myshop.plus.commons.utils.MapperUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wzh
 * @date 2020/1/2 - 11:02
 */
public class UploadFeignFallback implements UploadFeign {
    private static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";
    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(CodeMessage.FAIL.getCode(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
