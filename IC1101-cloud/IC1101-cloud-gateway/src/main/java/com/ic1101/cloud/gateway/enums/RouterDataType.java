package com.ic1101.cloud.gateway.enums;

/**
 * nachos配置方式枚举
 * @author cwd
 * @date 5/18/22 5:10 PM
 */
public enum RouterDataType {
    /**
     * 数据库加载路由配置
     */
    database,
    /**
     * 本地yml加载路由配置
     */
    yml,
    /**
     * nachos加载路由配置
     */
    nacos
}
