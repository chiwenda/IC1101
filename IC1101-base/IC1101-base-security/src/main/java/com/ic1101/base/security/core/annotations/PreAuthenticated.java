package com.ic1101.base.security.core.annotations;

import java.lang.annotation.*;

/**
 * @author ：chiwd
 * @description：用户需要登录注解
 * @date ：2022/6/9 21:59
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuthenticated {
}
