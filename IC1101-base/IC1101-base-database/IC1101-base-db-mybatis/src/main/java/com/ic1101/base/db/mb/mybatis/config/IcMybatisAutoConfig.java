package com.ic1101.base.db.mb.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置
 *
 * @author cwd
 * @date 6/14/22 4:47 PM
 */
@Configuration
public class IcMybatisAutoConfig {


    /**
     * 拦截器配置
     *
     * @author chiwenda
     * @date 6/14/22 4:51 PM
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return interceptor;
    }


    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new DefaultDB
    }
}
