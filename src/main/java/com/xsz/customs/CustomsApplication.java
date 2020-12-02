package com.xsz.customs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.xsz.customs.mapper")
@EnableScheduling
public class CustomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomsApplication.class, args);
    }

}
