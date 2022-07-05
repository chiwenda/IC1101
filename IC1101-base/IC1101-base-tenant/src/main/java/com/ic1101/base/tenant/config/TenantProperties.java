package com.ic1101.base.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * @author ：chiwd
 * @description：多租户属性
 * @date ：2022/7/5 21:53
 */
@Data
@ConfigurationProperties(prefix = "ic1101.tenant")
public class TenantProperties {

    /**
     * 是否开启多租户
     */
    private static Boolean ENABLE = true;

    private Boolean enable = ENABLE;


    /**
     * 忽略多租户的请求
     */
    private Set<String> ignoreUrls;


    /**
     * 忽略多租户的表
     */
    private Set<String> ignoreTables;

}
