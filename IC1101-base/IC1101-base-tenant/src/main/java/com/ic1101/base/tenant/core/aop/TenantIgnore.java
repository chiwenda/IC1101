package com.ic1101.base.tenant.core.aop;

import java.lang.annotation.*;

/**
 * @author ：chiwd
 * @description：忽略多租户注解
 * @date ：2022/7/5 22:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TenantIgnore {
}
