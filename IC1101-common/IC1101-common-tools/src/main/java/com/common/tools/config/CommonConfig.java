package com.common.tools.config;

import com.common.tools.utils.SpringContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringContextHolder注册用
 *
 * @author cwd
 * @date 2022/5/17 下午2:15
 */
@Configuration
public class CommonConfig {

    /**
     * Spring上下文工具配置
     */
    @Bean
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
