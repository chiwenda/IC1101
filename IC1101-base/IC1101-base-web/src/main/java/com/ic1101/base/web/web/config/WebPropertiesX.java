package com.ic1101.base.web.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author cwd
 * @date 6/13/22 9:52 AM
 */
@ConfigurationProperties("ic1101.web")
@Data
public class WebPropertiesX {

    @NotNull(message = "APP API not be null")
    private Api appApi;

    @NotNull(message = "Admin API not be null")
    private Api adminApi;

    @NotNull(message = "Admin UI not be null")
    private Ui adminUi;


    @Valid
    @Data
    public static class Api {

        @NotEmpty(message = "API 前缀不能为空")
        private String prefix;

        @NotEmpty(message = "Controller 所在包不能为空")
        private String controller;
    }

    @Data
    public static class Ui {

        /**
         * 访问地址
         */
        private String url;
    }
}
