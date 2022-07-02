package com.ic1101.base.db.mb.mybatis.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.KingbaseKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.ic1101.base.db.mb.mybatis.core.context.TenantHolder;
import com.ic1101.base.db.mb.mybatis.core.handler.DbFieldHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Objects;

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
     * 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
     * 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false
     *
     * @author chiwenda
     * @date 6/14/22 4:51 PM
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        //多租户插件配置，
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                Integer tenantId = TenantHolder.getTenantId();
                return new LongValue(tenantId);
            }

            /**
             * 忽略的表，默认返回 false 表示所有表都需要拼多租户条件
             * @param tableName
             * @return
             */
            @Override
            public boolean ignoreTable(String tableName) {
                return false;
            }
        }));
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }


    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new DbFieldHandler();
    }

    /**
     * 根据不同数据库配置不同key插入策略
     *
     * @param environment
     */
    @Bean
    @ConditionalOnProperty(prefix = "mybatis-plus.global-config.db-config", name = "id-type", havingValue = "INPUT")
    public IKeyGenerator keyGenerator(ConfigurableEnvironment environment) {
        DbType dbType = IdTypeEnvironmentPostProcessor.getDbType(environment);
        if (Objects.nonNull(dbType)) {
            switch (dbType) {
                case POSTGRE_SQL:
                    return new PostgreKeyGenerator();
                case ORACLE:
                case ORACLE_12C:
                    return new OracleKeyGenerator();
                case H2:
                    return new H2KeyGenerator();
                case KINGBASE_ES:
                    return new KingbaseKeyGenerator();
            }
        }
        throw new IllegalArgumentException(
                StrUtil.format("########## 【keyGenerator】{}cannot find IKeyGenerator ##########", dbType));
    }
}
