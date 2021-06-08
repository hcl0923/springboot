package com.yc.hcl.foodstore.food.controller;

import com.yc.hcl.foodstore.food.bean.Resfood;
import com.yc.hcl.foodstore.food.biz.ResFoodBiz;
import com.yc.hcl.foodstore.food.utils.YcConstants;
import com.yc.hcl.foodstore.food.vo.CartItem;
import com.yc.hcl.foodstore.food.vo.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.yc.hcl.foodstore.food.utils.YcConstants.RESFOODLIST;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-05 11:56
 */
@RestController
public class ResorderController {
    @Autowired
    private ResFoodBiz resFoodBiz;

    @RequestMapping(value = "/getCartInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel getCartInfoOp(HttpServletRequest req, HttpSession session, JsonModel jm) throws Exception {

        List<CartItem> list = new ArrayList<>();
        if (session.getAttribute(YcConstants.CART) != null) {
            jm.setCode(1);
            //变更,将对象改为list，方便后面循环取值
            Map<Integer, CartItem> cart = null;
            cart = (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
            Set<Integer> sets = cart.keySet();
            //可以直接cart.EntiySet();
            Iterator<Integer> iterator = sets.iterator();
            while (iterator.hasNext()) {
                int x = iterator.next();
                list.add(cart.get(x));
            }
            jm.setObj(list);
        } else {
            jm.setCode(0);
        }
        return jm;
    }

    @RequestMapping(value = "/delorder", method = RequestMethod.GET)
    public JsonModel delorder(HttpSession session, JsonModel jm, Resfood food) {
        int fid = food.getFid();
        //购物车跟用户相关，所以存session
        Map<Integer, CartItem> cart = null;
        if (session.getAttribute(YcConstants.CART) != null) {
            cart = (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
        } else {
            cart = new HashMap<Integer, CartItem>();
        }
        if (cart.containsKey(fid)) {
            cart.remove(fid);
            jm.setCode(1);
        } else {
            jm.setCode(0);
        }
        // 将cart存到session中
        session.setAttribute(YcConstants.CART, cart);
        return jm;
    }

    @RequestMapping(value = "/clearAll", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel clearAllopl(HttpSession session, JsonModel jm) {
        session.removeAttribute(YcConstants.CART);
        jm.setCode(1);
        return jm;
    }

    @RequestMapping(value = "/orderJson", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel orderJsonOp(HttpServletRequest req, JsonModel jm, HttpSession session) {
        commonOrder(req, session);
        jm.setCode(1);
        return jm;
    }

    private void commonOrder(HttpServletRequest req, HttpSession session) {
        int fid = Integer.parseInt(req.getParameter("fid"));
        int num = Integer.parseInt(req.getParameter("num"));
        List<Resfood> list = null;
        if (session.getAttribute(RESFOODLIST) != null) {
            list = (List<Resfood>) session.getAttribute(RESFOODLIST);
        } else {
            // 2．没有,则查
            list = resFoodBiz.findAll();
            session.setAttribute(YcConstants.RESFOODLIST, list);
        }
        //看看是不是我的菜
        Resfood food = null;
        for (Resfood r : list) {
            if (r.getFid() == fid) {
                food = r;
                break;
            }
        }
        //购物车跟用户相关，所以存session
        Map<Integer, CartItem> cart = null;
        //是否是第一次购买，如果是，则session中是没有存  购物车
        if (session.getAttribute(YcConstants.CART) != null) {
            cart = (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
        } else {
            cart = new HashMap<Integer, CartItem>();
        }
        //看这个购物车是否有 fid
        CartItem ci = null;
        if (cart.containsKey(fid)) {
            //证明用户已经购买了这个菜，则数量增加
            ci = cart.get(fid);
            int newnum = ci.getNum() + num;
            ci.setNum(newnum);
        } else {
            //还没有买过，则创建一个cartItem存到map中
            ci = new CartItem();
            ci.setFood(food);
            ci.setNum(num);
        }
        //如果购买项的数量小于等于0，则从购物车删除
        if (ci.getNum() <= 0) {
            cart.remove(fid);
        } else {
            ci.getSmallCount();//计算小计,
            // 将cantitem存到map中
            cart.put(fid, ci);
        }
        //将cart存到 session中
        session.setAttribute(YcConstants.CART, cart);
    }
}
