package com.yc.hcl.foodstore.food.biz;

import com.yc.hcl.foodstore.food.bean.Resuser;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-01 21:34
 */
public interface ResuserBiz {

    /**
     * 登录业务
     */
    public Resuser login(Resuser user);
}
