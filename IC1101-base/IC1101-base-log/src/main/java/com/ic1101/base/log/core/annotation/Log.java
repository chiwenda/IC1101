package com.ic1101.base.log.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：chiwd
 * @description：日志注解
 * @date ：2022/7/7 20:44
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 模块
     */
    String module() default "";

    /**
     * 名称
     */
    String name() default "";

    /**
     * 是否记录操作日志
     */
    boolean enable() default true;
}
