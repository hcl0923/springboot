package com.yc.hcl.foodstore.food.biz;

import com.yc.hcl.foodstore.food.bean.Resfood;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class ResFoodBizImplTest {

    @Autowired
    private ResFoodBiz resFoodBiz;

    @Test
    void findAll() {
        List<Resfood> list = resFoodBiz.findAll();
        System.out.println(list);
    }

    @Test
    void findByFid() {
        Resfood f = resFoodBiz.findByFid(13);
        Assert.assertNotNull(f);
    }
}