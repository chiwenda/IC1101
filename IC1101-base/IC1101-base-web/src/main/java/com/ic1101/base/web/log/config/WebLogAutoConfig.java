package com.ic1101.base.web.log.config;

import com.ic1101.base.web.web.config.WebAutoConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：chiwd
 * @description：日志配置
 * @date ：2022/7/3 14:21
 */
@Configuration
@AutoConfigureAfter(WebAutoConfig.class)
public class WebLogAutoConfig {
}
