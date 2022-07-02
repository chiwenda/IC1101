package com.ic1101.base.db.mb.mybatis.core.enums;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * @author ：chiwd
 * @description：SQL相关常量类
 * @date ：2022/7/2 18:16
 */
public class SqlConstants {

    /**
     * mybatis-plus数据库类型
     */
    public static DbType DB_TYPE;

    public static void init(DbType dbType) {
        DB_TYPE = dbType;
    }
}
