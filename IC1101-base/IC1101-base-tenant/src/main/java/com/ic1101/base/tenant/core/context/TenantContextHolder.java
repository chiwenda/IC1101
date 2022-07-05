package com.ic1101.base.tenant.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author ：chiwd
 * @description：多租户上下文
 * @date ：2022/7/5 22:07
 */
public class TenantContextHolder {

    //当前租户编号
    private static ThreadLocal<Long> TENANT_ID = new TransmittableThreadLocal<>();

    /**
     * 是否忽略租户
     */
    private static final ThreadLocal<Boolean> IGNORE = new TransmittableThreadLocal<>();

    public static Long getTenantId() {
        return TENANT_ID.get();
    }

    public static void setTenantId(Long tenantId) {
        TENANT_ID.set(tenantId);
    }

    public static void setIgnore(Boolean ignore) {
        IGNORE.set(ignore);
    }

    public static boolean isIgnore() {
        return Boolean.TRUE.equals(IGNORE.get());
    }

    public static void clear() {
        TENANT_ID.remove();
        IGNORE.remove();
    }
}
