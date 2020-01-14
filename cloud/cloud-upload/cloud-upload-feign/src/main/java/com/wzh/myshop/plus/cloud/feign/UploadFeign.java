package com.wzh.myshop.plus.cloud.feign;

import com.wzh.myshop.plus.cloud.feign.fallback.UploadFeignFallback;
import com.wzh.myshop.plus.config.interceptor.FeignRequestConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wzh
 * @date 2020/1/2 - 11:01
 */
@FeignClient(value = "cloud-upload", path = "upload", configuration = FeignRequestConfig.class, fallback = UploadFeignFallback.class)
public interface UploadFeign {
    @PostMapping
    String upload(MultipartFile multipartFile);
}
