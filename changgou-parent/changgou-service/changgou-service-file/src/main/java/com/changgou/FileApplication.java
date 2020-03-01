package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author DL_Wu
 * @Date 2020/3/1 12:42
 * @Version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)   //排除数据库自动加载
@EnableEurekaClient  // 开启Eureka 客户端
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class,args);
    }

}
