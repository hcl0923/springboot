package com.yc.hcl.foodstore.food.dao;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-02 19:11
 */

import com.yc.hcl.foodstore.food.bean.Resorder;
import com.yc.hcl.foodstore.food.enums.OrderStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResorderDaoTest {
    @Autowired
    private ResorderDao dao;

    @Test
    public void addResorder() {
        Resorder ro = new Resorder();
        ro.setAddress("湖南");
        ro.setDeliverytime(new Timestamp(new Date().getTime()));
        ro.setOrdertime(new Timestamp(new Date().getTime()));
        ro.setPs(" quick");
        ro.setTel("13834345656");
        ro.setStatus(OrderStatusEnum.NEW.getCode()); //枚举ro.setUserid(3);
        ro = dao.save(ro);
        //jipanepositony自动会返回一个存好的被
        //jpa 托管的对象
        System.out.println(ro.getRoid());
    }
}