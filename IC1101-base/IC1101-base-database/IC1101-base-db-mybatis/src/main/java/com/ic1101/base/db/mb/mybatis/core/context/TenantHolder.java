package com.ic1101.base.db.mb.mybatis.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 园区上下文
 *
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/2 10:20
 */
public class TenantHolder {

    //线程共享
    private static ThreadLocal<Integer> TENANT_ID = new TransmittableThreadLocal<>();

    public static void setTenantId(Integer tenantId) {
        TENANT_ID.set(tenantId);
    }

    public static Integer getTenantId() {
        return TENANT_ID.get();
    }
}
