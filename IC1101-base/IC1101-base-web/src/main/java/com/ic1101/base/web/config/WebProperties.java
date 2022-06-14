package com.ic1101.base.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author cwd
 * @date 6/13/22 9:52 AM
 */
@ConfigurationProperties("ic1101.web")
@Data
public class WebProperties {

    @NotNull(message = "APP API 不能为空")
    private Api appApi;
    @NotNull(message = "Admin API 不能为空")
    private Api adminApi;

    @NotNull(message = "Admin UI 不能为空")
    private Ui adminUi;


    @Data
    public static class Api {

        @NotEmpty(message = "API 前缀不能为空")
        private String prefix;

        @NotEmpty(message = "Controller 所在包不能为空")
        private String controller;
    }

    @Data
    public static class Ui{

        /**
         * 访问地址
         */
        private String url;
    }
}
