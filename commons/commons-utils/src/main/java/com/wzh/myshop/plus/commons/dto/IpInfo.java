package com.wzh.myshop.plus.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wzh
 * @date 2020/1/13 - 14:18
 */
@Data
public class IpInfo implements Serializable {

    private static final long serialVersionUID = -6246313701294144575L;

    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String county;
    private String isp;
    private String country_id;
    private String area_id;
    private String region_id;
    private String city_id;
    private String county_id;
    private String isp_id;
}
