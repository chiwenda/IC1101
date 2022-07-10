package com.ic1101.base.tenant.core.db;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.ic1101.base.tenant.config.TenantProperties;
import com.ic1101.base.tenant.core.context.TenantContextHolder;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/5 22:21
 */
public class TenantInterceptor implements TenantLineHandler {

    private final Set<String> ignoreTables = new HashSet<>();


    public TenantInterceptor(TenantProperties properties) {
        properties.getIgnoreTables().forEach(table -> {
            ignoreTables.add(table.toLowerCase());
            ignoreTables.add(table.toUpperCase());
        });
        //oracle
        ignoreTables.add("DUAL");
    }

    @Override
    public Expression getTenantId() {
        return new LongValue(TenantContextHolder.getRequiredTenantId());
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return TenantContextHolder.isIgnore()
                || CollUtil.contains(ignoreTables, tableName);
    }
}
