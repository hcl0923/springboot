package com.yc.starter.hellostarter.services;

/**
 * @program: springboot
 * @description:业务处理类
 * @author: 作者
 * @create: 2021-04-23 21:32
 */
public class HelloService implements IHelloService {
    private String content;
    private String name;
    
    public HelloService(String content, String name) {
        this.content = content;
        this.name = name;
    }

    @Override
    public String say() {
        return this.name + "快乐的说：" + this.content;
    }
}
