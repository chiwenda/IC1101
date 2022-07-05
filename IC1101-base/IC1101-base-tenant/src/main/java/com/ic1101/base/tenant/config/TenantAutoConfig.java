package com.ic1101.base.tenant.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/5 21:57
 */
@Configuration
@ConditionalOnProperty(prefix = "ic1101.tenant",value = "enable",matchIfMissing = true)
@EnableConfigurationProperties(TenantProperties.class)
public class TenantAutoConfig {
}
