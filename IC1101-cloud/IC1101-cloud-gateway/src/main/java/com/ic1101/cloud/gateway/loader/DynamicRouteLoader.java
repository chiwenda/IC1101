package com.ic1101.cloud.gateway.loader;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.common.tools.base.BaseMap;
import com.common.tools.utils.RedisUtil;
import com.google.common.collect.Lists;
import com.ic1101.cloud.gateway.config.GatewayRoutersConfiguration;
import com.ic1101.cloud.gateway.enums.RouterDataType;
import com.ic1101.cloud.gateway.loader.repository.MyInMemoryRouteDefinitionRepository;
import com.ic1101.cloud.gateway.loader.service.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * 动态路由加载器
 *
 * @author cwd
 * @date 5/18/22 10:05 AM
 */
@Slf4j
@Component
@DependsOn("gatewayRoutersConfiguration")
@RefreshScope
public class DynamicRouteLoader implements ApplicationEventPublisherAware {


    private MyInMemoryRouteDefinitionRepository repository;
    private ApplicationEventPublisher publisher;
    private DynamicRouteService routeService;
    private RedisUtil redisUtil;
    private ConfigService configService;


    /**
     * 路由配置方式：database(数据库)，yml(配置文件)，nacos
     */
    @Value("${ic1101.route.config.data-type:database}")
    public String dataType;

    /**
     * 需要拼接key的路由条件
     */
    private static String[] GEN_KEY_ROUTERS = new String[]{"Path", "Host", "Method", "After", "Before", "Between", "RemoteAddr"};

    public DynamicRouteLoader(MyInMemoryRouteDefinitionRepository repository, DynamicRouteService routeService,
                              RedisUtil redisUtil) {
        this.repository = repository;
        this.routeService = routeService;
        this.redisUtil = redisUtil;
    }


    public void init(BaseMap baseMap) {
        log.info("初始化路由，dataType：" + dataType);
        if (RouterDataType.nacos.toString().endsWith(dataType)) {

        }
    }


    /**
     * 从nacos读取路由配置
     */
    private void loadRoutesByNacos() {
        List<RouteDefinition> routes = Lists.newArrayList();
        configService = createConfigService();
        if (configService == null) {
            log.warn("init nacos config service fail");
        }
        try {
            String config = configService.getConfig(GatewayRoutersConfiguration.DATA_ID, GatewayRoutersConfiguration.ROUTE_GROUP,
                    GatewayRoutersConfiguration.DEFAULT_TIMEOUT);
            if (StringUtils.isNotBlank(config)) {
                log.info("获取网关配置:\r\n{}", config);
            }
        } catch (NacosException e) {

        }
    }

    /**
     * 创建ConfigService
     */
    private ConfigService createConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", GatewayRoutersConfiguration.SERVER_ADDR);
            properties.setProperty("namespace", GatewayRoutersConfiguration.NAMESPACE);
            properties.setProperty("username", GatewayRoutersConfiguration.USERNAME);
            properties.setProperty("password", GatewayRoutersConfiguration.PASSWORD);
            return configService = NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            log.error("create ConfigService error:{}", e.getErrMsg());
            return null;
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

}
