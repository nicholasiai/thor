package com.likai.thor.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.likai.thor.dao")
@ComponentScan(basePackages = {"com.likai.thor"})
public class ThorApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ThorApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ThorApplication.class);
    }
}
