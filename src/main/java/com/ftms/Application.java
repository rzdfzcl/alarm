package com.ftms;

import com.ftms.alarm.App;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = {"com.ftms.alarm.domain"})
@MapperScan(basePackages = {"com.ftms.alarm.mapper"})
@EnableConfigurationProperties
public class Application {
    public static void main(String[] args)  throws Exception {
        SpringApplication.run(Application.class, args);
        //模拟节点控制器
        new App().alarmCenter();
    }
}
