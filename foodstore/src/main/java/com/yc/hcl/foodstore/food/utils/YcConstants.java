package com.yc.hcl.foodstore.food.utils;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-05 10:57
 */
public class YcConstants {
    public static final String RESFOODLIST = "resfoodlist";//session中对应的菜品列表的键
    public final static String ERROR_404 = "common/404.html";
    public final static String ERROR_500 = "common/500.html";
    //购物车的session的键名
    public final static String CART = "cart";
    //查看单个商品详情的地址
    public final static String EINDFOOD = "findFood";
    public final static String ORDERFOOD = "order";
    //cookie中浏览过的商品编号
    public final static String BROWSEDFOOD = "browsedfood";
    //登录用户的sessionid
    public static final String LOGINUSER = "loginuser";
    //上一次访问的页面地址
    public final static String LASTVISITEDADDR = "lastvisitedaddr";
    //首页地址
    public final static String HOMEPAGE = "index.html";
    //下定成功后返回的页面 ->真实的项目，这里是支付页面
    public final static String ORDERSUCCESSADDR = "index.html";

}
