package com.ic1101.base.web.jackson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ic1101.base.web.jackson.core.databind.LocalDateTimeDeserializer;
import com.ic1101.base.web.jackson.core.databind.LocalDateTimeSerializer;
import com.ic1101.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/3 21:14
 */
@Slf4j
@Configuration
public class JacksonAutoConfig {


    @Bean
    public BeanPostProcessor objectMapperBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof ObjectMapper)) {
                    return bean;
                }

                ObjectMapper objectMapper = (ObjectMapper) bean;
                SimpleModule simpleModule = new SimpleModule();

                simpleModule.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                        .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INStANCE);

                objectMapper.registerModule(simpleModule);


                JsonUtils.init(objectMapper);

                log.info("########## 【JacksonAutoConfig】init jackson auto config success ##########");
                return bean;
            }
        };
    }
}
