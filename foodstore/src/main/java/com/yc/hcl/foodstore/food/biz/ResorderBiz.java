package com.yc.hcl.foodstore.food.biz;

import com.yc.hcl.foodstore.food.bean.Resorder;
import com.yc.hcl.foodstore.food.vo.CartItem;

import java.util.Map;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-01 21:34
 */
public interface ResorderBiz {

    public Integer completeOrder(Resorder resorder, Map<Integer, CartItem> shopCart);
}
