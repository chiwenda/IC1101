package com.ic1101.base.web.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;


/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/4 21:22
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
@ConditionalOnClass({Docket.class, ApiInfoBuilder.class})
@ConditionalOnProperty(prefix = "ic1101.swagger", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerPropertiesX.class)
public class SwaggerAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public SwaggerPropertiesX swaggerProperties(){
        return new SwaggerPropertiesX();
    }
    @Bean
    public Docket createRestApi(){
        SwaggerPropertiesX swaggerPropertiesX = new SwaggerPropertiesX();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swaggerPropertiesX))
                // 设置扫描指定 package 包下的
                .select()
                .apis(basePackage(swaggerPropertiesX.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .globalRequestParameters(globalRequestParameters())
                .securityContexts(securityContexts());
    }

    /**
     * API 摘要信息
     */
    private static ApiInfo apiInfo(SwaggerPropertiesX properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .contact(new Contact(properties.getAuthor(), null, null))
                .version(properties.getVersion())
                .build();
    }
}
