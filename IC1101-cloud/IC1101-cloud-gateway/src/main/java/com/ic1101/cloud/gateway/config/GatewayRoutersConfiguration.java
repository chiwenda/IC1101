package com.ic1101.cloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * 路由配置
 *
 * @author cwd
 * @date 5/18/22 10:13 AM
 */
@Slf4j
@RefreshScope
@Configuration
public class GatewayRoutersConfiguration {
    public static final long DEFAULT_TIMEOUT = 30000;
    public static String SERVER_ADDR;
    public static String NAMESPACE;
    public static String DATA_ID;
    public static String ROUTE_GROUP;
    public static String USERNAME;
    public static String PASSWORD;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    public void setServerAddr(String serverAddr) {
        SERVER_ADDR = serverAddr;
    }

    @Value("${spring.cloud.nacos.config.username}")
    public void setUserName(String userName) {
        USERNAME = userName;
    }

    @Value("${spring.cloud.nacos.config.password}")
    public void setPassword(String password) {
        PASSWORD = password;
    }


    @Value("${jeecg.route.config.data-id:#{null}}")
    public void setRouteDataId(String dataId) {
        DATA_ID = dataId + ".json";
    }

    @Value("${jeecg.route.config.group:DEFAULT_GROUP:#{null}}")
    public void setRouteGroup(String routeGroup) {
        ROUTE_GROUP = routeGroup;
    }


    /**
     * swagger接口地址（通过9999端口直接访问）
     */
    @Bean
    public RouterFunction<ServerResponse> indexRouter(
            @Value("classpath:/META-INF/resources/doc.html") final Resource indexHtml) {
        return route(GET("/"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
    }
}
