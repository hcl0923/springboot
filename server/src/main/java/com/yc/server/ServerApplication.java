package com.yc.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class ServerApplication {
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(ServerApplication.class, args);
//    }
//
//}
@SpringBootApplication
public class ServerApplication extends SpringBootServletInitializer {
    private static Log log = LogFactory.getLog(ServerApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        log.info("启动服务器");
        return application.sources(ServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
