package com.ic1101.base.tenant.core.aop;

import com.ic1101.base.tenant.core.context.TenantContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author ：chiwd
 * @description：切面
 * @date ：2022/7/5 22:01
 */
@Aspect
public class TenantAspect {

    /**
     * 环绕通知，对注解环绕通知执行逻辑
     *
     * @param joinPoint    切入点
     * @param tenantIgnore 忽略
     */
    @Around("@annotation(tenantIgnore)")
    public Object around(ProceedingJoinPoint joinPoint, TenantIgnore tenantIgnore) throws Throwable {
        boolean ignore = TenantContextHolder.isIgnore();
        try {
            TenantContextHolder.setIgnore(true);
            return joinPoint.proceed();
        } finally {
            TenantContextHolder.setIgnore(ignore);
        }
    }
}
