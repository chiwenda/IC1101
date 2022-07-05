package com.ic1101.base.web.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/4 21:20
 */
@ConfigurationProperties("ic1101.swagger")
@Data
public class SwaggerPropertiesX {
    @NotEmpty(message = "title not be null")
    private String title;

    @NotEmpty(message = "description not be null")
    private String description;

    @NotEmpty(message = "author not be null")
    private String author;

    @NotEmpty(message = "version not be null")
    private String version;

    @NotEmpty(message = "scan package not be null")
    private String basePackage;
}
