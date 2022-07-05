package com.ic1101.base.web.web.config;

import com.ic1101.common.enums.WebFilterOrderEnum;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * @author ：chiwd
 * @description：webmvc配置
 * @date ：2022/7/3 11:54
 */
@Configuration
@EnableConfigurationProperties(WebPropertiesX.class)
public class WebAutoConfig implements WebMvcConfigurer {

    @Resource
    private WebPropertiesX webPropertiesX;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //后台controller路径匹配
        configurePathMatch(configurer, webPropertiesX.getAdminApi());
        //移动端路径匹配
        configurePathMatch(configurer, webPropertiesX.getAppApi());
    }

    private void configurePathMatch(PathMatchConfigurer configurer, WebPropertiesX.Api api) {
        AntPathMatcher pathMatcher = new AntPathMatcher(".");
        //添加自定义前缀,仅仅匹配controller包
        configurer.addPathPrefix(api.getPrefix(), clazz -> clazz.isAnnotationPresent(RestController.class)
                && pathMatcher.match(api.getController(), clazz.getPackage().getName()));
    }

    /**
     * 跨域处理
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterBean() {
        // 创建 CorsConfiguration 对象
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // 设置访问源地址
        config.addAllowedHeader("*"); // 设置访问源请求头
        config.addAllowedMethod("*"); // 设置访问源请求方法
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return createFilterBean(new CorsFilter(source), WebFilterOrderEnum.CORS_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setOrder(order);
        return registrationBean;
    }


}
