package com.yc.spring.springboot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-18 10:21
 */
@RestController      //@Controller控制层@Restfull以rest规范(请求方式：get,post,put,delete,json)发送请求和响应
public class Hello {
    //创建日志对象                            必须写当前类的反射类
    private static Log log = LogFactory.getLog(Hello.class);

    @Autowired
    private Environment env;

    @Value("${yc.tname}")
    private String tname;

    @Autowired
    private YcProperties yp;

    @GetMapping("/hello")                     //请求对象 get   ,请求路径为: hello
    public String Hello(@RequestParam("name") String name) {
        log.debug("********debug*********");
        log.info("********info*********");
        log.error("********error*********");
        log.fatal("********fatal*********");

        log.info("系统环境变量信息如下：" + env);
        log.info("用户路径：" + env.getProperty("user.home"));

        log.info("" + env.getProperty("JAVA_HOME"));
        log.info("yc.tname：" + tname);
        log.info("YcProperties中的属性：" + yp.getTname() + "\t" + yp.getAge());
        log.info("YcProperties中的属性：" + yp.getTname() + "\t" + yp.getAge());
        log.info("env中是否也可以拿到呢？" + env.getProperty("yc.tname") + "\t" + yp.getAge());


        return String.format("Hello %s", name);

    }
}
