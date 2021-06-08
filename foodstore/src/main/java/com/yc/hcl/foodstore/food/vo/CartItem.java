package com.yc.hcl.foodstore.food.vo;

import com.yc.hcl.foodstore.food.bean.Resfood;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: springboot
 * @description:购物车项
 * @author: 作者
 * @create: 2021-05-04 18:47
 */
public class CartItem implements Serializable {
    private static final long serialVersionUID = 5671270750267931030L;

    private Resfood food;
    private int num;
    private BigDecimal smallCount;

    /**
     * 计算当前这个Cart中商品小计
     */
    public BigDecimal getSmallCount() {
        //multply
        this.smallCount = food.getRealprice().multiply(new BigDecimal(num));
        return smallCount;
    }

    public void setFood(Resfood food) {
        this.food = food;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Resfood getFood() {
        return food;
    }

    public int getNum() {
        return num;
    }

}
