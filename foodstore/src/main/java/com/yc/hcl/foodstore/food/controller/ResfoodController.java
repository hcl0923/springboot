package com.yc.hcl.foodstore.food.controller;

import com.yc.hcl.foodstore.food.bean.Resfood;
import com.yc.hcl.foodstore.food.bean.Resorder;
import com.yc.hcl.foodstore.food.bean.Resuser;
import com.yc.hcl.foodstore.food.biz.ResFoodBiz;
import com.yc.hcl.foodstore.food.biz.ResorderBiz;
import com.yc.hcl.foodstore.food.utils.YcConstants;
import com.yc.hcl.foodstore.food.vo.CartItem;
import com.yc.hcl.foodstore.food.vo.JsonModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.yc.hcl.foodstore.food.utils.YcConstants.RESFOODLIST;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-05 10:14
 */
@Api(description = "小萌神网络订餐系统操作接口", tags = {"菜品操作部分", "控制层"})
@Slf4j
@RestController//@Controller @ResponseBody
public class ResfoodController {

    @Autowired
    private ResFoodBiz resFoodBiz;

    @Autowired
    private ResorderBiz resorderBiz;

    @RequestMapping(value = "confirmOrder")
    public JsonModel confirmOrder(HttpSession session, Resorder resorder, JsonModel jm) {
        if (session.getAttribute(YcConstants.LOGINUSER) == null) {
            jm.setCode(0);
            jm.setMsg("user has not been logined.. ..");
            return jm;
        }
        //查询用户id从session中取出登录用户，
        Resuser resuser = (Resuser) session.getAttribute(YcConstants.LOGINUSER);
        resorder.setUserid(resuser.getUserid());
        //准备 Resonderitem数据
        if (session.getAttribute(YcConstants.CART) == null && ((Map<Integer, CartItem>)
                session.getAttribute(YcConstants.CART)).size() <= 0) {
            jm.setCode(0);
            jm.setMsg("you have not buy any thing.. ..");
            return jm;
        }
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
        try {
            resorderBiz.completeOrder(resorder, cart);
            session.removeAttribute(YcConstants.CART);
            jm.setCode(1);
        } catch (Exception e) {
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        return jm;
    }

    @ApiOperation(value = "查询所有菜", notes = "查询所有的菜")
    @RequestMapping(value = "/findAllFoods", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel findAllFoods(HttpSession session, JsonModel jm) {//这里的方法的参数不是从界面取的，而是由springmvc创建后传入空对象
        List<Resfood> list = resFoodBiz.findAll();
        session.setAttribute(RESFOODLIST, list);
        //返回jsonModel
        jm.setCode(1);
        jm.setObj(list);
        return jm;
    }

    @RequestMapping(value = "/findFood", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "根据fid查找菜品", notes = "根据fid查找菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fid", value = "菜品编号", required = true)})
    public JsonModel findFood(HttpSession session, Resfood food, JsonModel jm) {
        List<Resfood> list = null;
        if (session.getAttribute(RESFOODLIST) != null) {
            list = (List<Resfood>) session.getAttribute(RESFOODLIST);
        } else {
            //2．没有，则查
            list = resFoodBiz.findAll();
            session.setAttribute(RESFOODLIST, list);
        }
        for (Resfood r : list) {
            if (food.getFid().equals(r.getFid())) {
                jm.setCode(1);
                jm.setObj(r);
                return jm;
            }
        }
        jm.setCode(0);
        return jm;
    }
}