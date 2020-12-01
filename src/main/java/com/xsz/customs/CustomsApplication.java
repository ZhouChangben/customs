package com.xsz.customs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xsz.customs.mapper")
public class CustomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomsApplication.class, args);
    }

}
