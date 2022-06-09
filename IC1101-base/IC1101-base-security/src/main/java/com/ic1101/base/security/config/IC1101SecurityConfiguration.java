package com.ic1101.base.security.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;

/**
 * Spring Security 配置
 *
 * @author cwd
 * @date 6/9/22 2:07 PM
 */

@EnableConfigurationProperties(SecurityProperties.class)
public class IC1101SecurityConfiguration {
    @Resource
    private SecurityProperties securityProperties;


}
