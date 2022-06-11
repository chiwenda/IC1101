package com.ic1101.base.security.config;

import com.ic1101.base.security.core.aop.PreAuthenticatedAspect;
import com.ic1101.base.security.core.hanler.AccessDeniedHandlerImpl;
import com.ic1101.base.security.core.hanler.AuthenticationEntryPointImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

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
     * 用户未登录拦截
     */
    @Bean
    public PreAuthenticatedAspect preAuthenticatedAspect() {
        return new PreAuthenticatedAspect();
    }


    /**
     * 认证失败处理
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不足处理
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }


    /**
     * 密码加密方法
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public TokenAuth



}
