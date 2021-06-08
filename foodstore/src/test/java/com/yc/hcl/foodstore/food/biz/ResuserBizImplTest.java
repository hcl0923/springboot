package com.yc.hcl.foodstore.food.biz;

import com.yc.hcl.foodstore.food.bean.Resuser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ResuserBizImplTest {

    @Autowired
    private ResuserBiz resuserBiz;

    @Test
    void login() {
        Resuser u = new Resuser();
        u.setUsername("a");
        u.setPwd("a");

        Resuser r = resuserBiz.login(u);
        Assert.assertNotNull(r);
    }
}