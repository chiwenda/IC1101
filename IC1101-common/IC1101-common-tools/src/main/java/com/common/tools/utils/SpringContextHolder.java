package com.common.tools.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author cwd
 * @date 2022/5/16 下午4:31
 */
@Slf4j
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getHanlder(String name, Class<T> cls) {
        T t = null;
        if (ObjectUtil.isEmpty(name)) {
            checkApplicationContext();
            try {
                t = applicationContext.getBean(name, cls);
            } catch (Exception e) {
                log.warn("Customize redis listener handle [ " + name + " ], does not exist！");
            }
        }
        return t;
    }

    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    public static void cleanApplicationContext() {
        applicationContext = null;
    }


    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("== applicationContext not inject, please define SpringContextHolder in applicationContext.xml ==");
        }
    }
}
