package com.stu.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/15 14:42
 * @Version: 1.0
 * @Description:
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.video.service", "com.stu.video.*"})
@MapperScan(basePackages = {"com.stu.video.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
