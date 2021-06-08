package com.yc.hcl.foodstore.food.biz;

import com.yc.hcl.foodstore.food.bean.Resfood;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-01 21:34
 */
public interface ResFoodBiz {

    /**
     * 查找所有的菜
     */
    public List<Resfood> findAll();

    /**
     * 根据fid查找某个菜
     */
    public Resfood findByFid(Integer fid);
}