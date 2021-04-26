package com.yc.starter.hellostarter.config;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-23 21:34
 */

import com.yc.starter.hellostarter.properties.HelloProperties;
import com.yc.starter.hellostarter.services.HelloService;
import com.yc.starter.hellostarter.services.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//这是自动化配置类
@EnableConfigurationProperties(HelloProperties.class)//启用配置属性类，以读取属性文件中对应的值
//@ConditionalOnProperty(prefix = "yc", name = "isopen", havingValue = "true")

public class HelloAutoConfiguration {
    @Autowired
    private HelloProperties helloProperties;

    @Bean//托管这个业务Bean
    public IHelloService helloService() {
        return new HelloService(helloProperties.getContent(), helloProperties.getName());
    }
}