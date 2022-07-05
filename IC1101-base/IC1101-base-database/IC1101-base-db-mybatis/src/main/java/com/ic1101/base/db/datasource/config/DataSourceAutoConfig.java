package com.ic1101.base.db.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ：chiwd
 * @description：数据源配置
 * @date ：2022/7/3 10:23
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties(DruidStatProperties.class)
public class DataSourceAutoConfig {

}
