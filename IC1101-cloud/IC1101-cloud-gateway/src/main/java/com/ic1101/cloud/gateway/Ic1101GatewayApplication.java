package com.ic1101.cloud.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cwd
 * @date 5/18/22 9:59 AM
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Ic1101GatewayApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Ic1101GatewayApplication.class, args);
        System.out.println(" gateway application run successfully!! ");
    }

    /**
     * 容器初始化后加载路由
     */
    @Override
    public void run(String... args) throws Exception {

    }
}
