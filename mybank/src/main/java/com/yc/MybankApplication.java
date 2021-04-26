package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MybankApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybankApplication.class, args);
    }

}
