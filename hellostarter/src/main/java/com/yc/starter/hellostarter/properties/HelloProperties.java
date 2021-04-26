package com.yc.starter.hellostarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取配置信恳的类
 *
 * @author hp
 */
@ConfigurationProperties(prefix = "yc.starter")
public class HelloProperties {
    private String content;
    private String name;

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }
}