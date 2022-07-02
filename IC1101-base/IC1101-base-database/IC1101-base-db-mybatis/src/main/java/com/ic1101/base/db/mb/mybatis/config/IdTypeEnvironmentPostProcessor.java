package com.ic1101.base.db.mb.mybatis.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.ic1101.base.db.mb.mybatis.core.enums.SqlConstants;
import com.ic1101.base.db.mb.mybatis.core.util.JdbcUtils;
import com.ic1101.common.util.collection.SetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Objects;
import java.util.Set;

/**
 * @author ：chiwd
 * @description：mybatis idType
 * @date ：2022/7/2 13:47
 */
@Slf4j
public class IdTypeEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String ID_TYPE_KEY = "mybatis-plus.global-config.db-config.id-type";

    private static final String DATASOURCE_DYNAMIC_KEY = "spring.datasource.dynamic";

    private static final Set<DbType> INPUT_DB_TYPES = SetUtils.asSet(DbType.ORACLE, DbType.ORACLE_12C,
            DbType.POSTGRE_SQL, DbType.KINGBASE_ES, DbType.DB2, DbType.H2);


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        DbType dbType = getDbType(environment);
        if (Objects.isNull(dbType)) {
            return;
        }

        SqlConstants.init(dbType);

        IdType idType = getIdType(environment);
        if (idType != IdType.NONE) {
            return;
        }

        if (INPUT_DB_TYPES.contains(dbType)) {
            setIdType(environment, IdType.INPUT);
            return;
        }
        setIdType(environment, IdType.AUTO);

    }

    public IdType getIdType(ConfigurableEnvironment environment) {
        return environment.getProperty(ID_TYPE_KEY, IdType.class);
    }

    public void setIdType(ConfigurableEnvironment environment, IdType idType) {
        //设置系统环境变量
        environment.getSystemEnvironment().put(ID_TYPE_KEY, idType);
        log.info("########## 【IdTypeEnvironmentPostProcessor】modify mybatis-plus idType as {} ##########", idType);
    }


    public static DbType getDbType(ConfigurableEnvironment environment) {
        String primary = environment.getProperty(DATASOURCE_DYNAMIC_KEY + ".primary");
        if (StrUtil.isEmpty(primary)) {
            return null;
        }

        String url = environment.getProperty(DATASOURCE_DYNAMIC_KEY + ".datasource." + primary + "url");
        if (StrUtil.isEmpty(url)) {
            return null;
        }

        return JdbcUtils.getDbType(url);
    }
}
