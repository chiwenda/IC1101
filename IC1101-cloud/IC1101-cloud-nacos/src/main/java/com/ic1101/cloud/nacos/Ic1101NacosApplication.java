package com.ic1101.cloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cwd
 * @date 5/18/22 6:42 PM
 */
@SpringBootApplication
public class Ic1101NacosApplication {

    private static String standalone = "true";

    private static String enableAuth = "false";


    public static void main(String[] args) {
        System.setProperty("nacos.standalone", standalone);
        System.setProperty("nacos.auth.enable,", enableAuth);
        System.setProperty("server.tomcat.basedir", "logs");
        //自定义启动端口号
        System.setProperty("server.port", "8848");
        SpringApplication.run(Ic1101NacosApplication.class, args);
    }

    /**
     * 默认跳转首页
     */
    @GetMapping("/")
    public String index() {
        // 视图重定向 - 跳转
        return "/nacos";
    }
}
