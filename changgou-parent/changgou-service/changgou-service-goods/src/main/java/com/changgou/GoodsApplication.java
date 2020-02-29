package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author DL_Wu
 * @Date 2020/2/28 18:32
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
/**
 * //开启通过Mapper的包扫描
 *      包名为tk.mybatis.spring.annotation.MapperScan
 */
@MapperScan(basePackages = {"com.changgou.dao"})
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }

}
