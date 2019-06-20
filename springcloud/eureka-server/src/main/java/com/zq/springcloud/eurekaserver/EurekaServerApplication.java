package com.zq.springcloud.eurekaserver;

import cn.hutool.core.util.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public  class EurekaServerApplication {

    public static void main(String[] args) {
        //8761这个端口是默认的,就不要修改了,后面的子项目,都会访问这个端口
        int port=8761;
        if(!NetUtil.isUsableLocalPort(port)){
            System.err.printf("端口%d不可用",port);
            System.exit(1);
        }
        new SpringApplicationBuilder(EurekaServerApplication.class).properties("server.port="+port).run(args);
    }

}
