package com.ic1101.base.security.config;

import com.ic1101.base.security.core.aop.PreAuthenticatedAspect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

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


    /**
     * 用户登录拦截
     */
    @Bean
    public PreAuthenticatedAspect preAuthenticatedAspect() {
        return new PreAuthenticatedAspect();
    }




}
