package com.ic1101.base.db.mb.mybatis.core.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author ：chiwd
 * @description：jdbc工具
 * @date ：2022/7/2 18:34
 */
public class JdbcUtils {

    /**
     * 判断数据库是否正确连接
     *
     * @param url
     * @param username
     * @param password
     * @return 是否连接
     */
    public static boolean isConnectionOK(String url, String username, String password) {
        if (StrUtil.isEmpty(url)) {
            return false;
        }

        try (Connection ignored = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 获取url对应的DB类型
     *
     * @param url
     * @return db类型
     */
    public static DbType getDbType(String url) {
        String dbType = com.alibaba.druid.util.JdbcUtils.getDbType(url, null);
        return DbType.getDbType(dbType);
    }
}
