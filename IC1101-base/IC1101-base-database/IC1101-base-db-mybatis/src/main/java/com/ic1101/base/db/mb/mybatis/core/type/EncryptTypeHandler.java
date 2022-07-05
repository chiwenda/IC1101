package com.ic1101.base.db.mb.mybatis.core.type;

import cn.hutool.core.lang.Assert;
import cn.hutool.extra.spring.SpringUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.jasypt.encryption.StringEncryptor;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author ：chiwd
 * @description：可通过 jasypt.encryptor.password 配置项，设置密钥
 * @date ：2022/7/3 09:40
 */
public class EncryptTypeHandler extends BaseTypeHandler<String> {

    private static StringEncryptor encryptor;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        //写入数据库时对字段加密
        ps.setString(i, getEncryptor().encrypt(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        //对查询结果字段解密
        return decrypt(value);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        //对查询结果字段解密
        return decrypt(value);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        //对查询结果字段解密
        return decrypt(value);
    }

    /**
     * 获取加解密工具
     */
    private static StringEncryptor getEncryptor() {
        if (Objects.nonNull(encryptor)) {
            return encryptor;
        }

        encryptor = SpringUtil.getBean(StringEncryptor.class);
        Assert.notNull(encryptor, "########## 【getEncryptor】encryptor not be null");
        return encryptor;
    }

    /**
     * 解密
     *
     * @param value
     * @return 解密后
     */
    private static String decrypt(String value) {
        if (Objects.isNull(value)) {
            return null;
        }

        return getEncryptor().decrypt(value);
    }

    /**
     * 加密
     *
     * @param rawValue
     * @return 加密后
     */
    private static String encrypt(String rawValue) {
        if (Objects.isNull(rawValue)) {
            return null;
        }

        return getEncryptor().encrypt(rawValue);
    }
}
