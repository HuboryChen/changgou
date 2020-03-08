package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author DL_Wu
 * @Date 2020/3/8 14:53
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.changgou.content.dao")   //开启mapper包扫描
public class ContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class,args);
    }
}
