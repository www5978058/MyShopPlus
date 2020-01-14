package com.wzh.myshop.plus.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wzh
 * @date 2020/1/2 - 10:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {
    private static final long serialVersionUID = -8766229195175760533L;
    /**
     * 文件路径
     */
    private String path;
}
