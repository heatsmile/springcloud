package com.zq.springcloud.turbine;

import cn.hutool.core.util.NetUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTurbine
public class TurbineApplication {

    public static void main(String[] args) {
        int port=8021;
        if(!NetUtil.isUsableLocalPort(port)){
            System.err.printf("端口%d被占用，无法使用%n",port);
            System.exit(1);
        }
        new SpringApplicationBuilder(TurbineApplication.class).properties("server.port="+port).run(args);
    }

}
