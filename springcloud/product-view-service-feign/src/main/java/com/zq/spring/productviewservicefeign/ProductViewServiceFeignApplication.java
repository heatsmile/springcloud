package com.zq.spring.productviewservicefeign;

import brave.sampler.Sampler;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class ProductViewServiceFeignApplication {

    public static void main(String[] args) {
        //判断rabbitMQ是否启动
        int rabbitMQPort=5672;
        if(NetUtil.isUsableLocalPort(rabbitMQPort)){
            System.err.printf("未在端口%d发现rabbitMQ服务，请检查rabbitMQ是否启动",rabbitMQPort);
            System.exit(1);
        }
        int port=0;
        int defaultProt=8012;
        Future<Integer> future= ThreadUtil.execAsync(()->{
            int p=0;
            System.out.println("请于5秒钟内输入端口号，推荐8012、8013或者8014，超过5秒默认使用"+defaultProt);
            Scanner sc=new Scanner(System.in);
            while(true){
                String strPort=sc.nextLine();
                if(!NumberUtil.isInteger(strPort)){
                    System.out.println("只能输入数字");
                    continue;
                }else{
                    p= Convert.toInt(strPort);
                    sc.close();
                    break;
                }
            }
            return p;
        });
        try {
            port=future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            port=defaultProt;
        }
        if(!NetUtil.isUsableLocalPort(port)){
            System.err.printf("端口%d被占用，无法使用%n",port);
            System.exit(1);
        }
        new SpringApplicationBuilder(ProductViewServiceFeignApplication.class).properties("server.port="+port).run(args);
    }


    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
