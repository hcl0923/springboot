package com.yc.hcl.foodstore.food.biz;

import com.yc.hcl.foodstore.food.bean.Resorder;
import com.yc.hcl.foodstore.food.enums.OrderStatusEnum;
import com.yc.hcl.foodstore.food.vo.CartItem;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
class ResorderBizImplTest {

    @Autowired
    private ResorderBiz resorderBiz;

    @Autowired
    private ResFoodBiz resFoodBiz;

    @Test
    public void completeOrder() {
        Resorder o = new Resorder();
        o.setUserid(1);
        o.setStatus(OrderStatusEnum.NEW.getCode());
        o.setTel("17382100518");
        o.setPs("快");
        o.setAddress("湖南工学院");
        //o.setDeliverytime(new TimeStamp(new Date().getTime()));
        Map<Integer, CartItem> cart = new HashMap<>();

        Integer fid1 = 11;
        CartItem ci1 = new CartItem();
        ci1.setFood(resFoodBiz.findByFid(fid1));
        ci1.setNum(1);

        Integer fid2 = 12;
        CartItem ci2 = new CartItem();
        ci2.setFood(resFoodBiz.findByFid(fid2));
        ci2.setNum(1);

        cart.put(fid1, ci1);
        cart.put(fid2, ci2);
        resorderBiz.completeOrder(o, cart);
    }
}