package com.ic1101.base.web.swagger.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

/**
 * @author ：chiwd
 * @description：解决 SpringFox 与 SpringBoot 2.6.x 不兼容的问题
 * @date ：2022/7/4 21:42
 */
public class SpringFoxBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof WebMvcRequestHandlerProvider|| bean instanceof WebFluxRequestHandlerProvider){

        }
        return bean;
    }
}
