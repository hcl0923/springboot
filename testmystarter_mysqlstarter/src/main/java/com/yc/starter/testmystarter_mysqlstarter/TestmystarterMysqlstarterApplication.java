package com.yc.starter.testmystarter_mysqlstarter;

import com.yc.starter.mysql_connectionspringbootstarter.IDBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;

@SpringBootApplication
@RestController
public class TestmystarterMysqlstarterApplication {
    @Autowired
    private IDBHelper idbHelper;

    public static void main(String[] args) {
        SpringApplication.run(TestmystarterMysqlstarterApplication.class, args);
    }
    
    @GetMapping("/con")
    public String testCon() {
        Connection con = idbHelper.getConnection();
        String constr = con.toString();
        return constr;
    }
}
