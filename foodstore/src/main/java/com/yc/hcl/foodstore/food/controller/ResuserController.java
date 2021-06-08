package com.yc.hcl.foodstore.food.controller;

import com.yc.hcl.foodstore.food.bean.Resuser;
import com.yc.hcl.foodstore.food.biz.ResuserBiz;
import com.yc.hcl.foodstore.food.utils.YcConstants;
import com.yc.hcl.foodstore.food.vo.JsonModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.yc.hcl.foodstore.food.utils.YcConstants.LOGINUSER;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-05 11:43
 */
@RestController
public class ResuserController {
    @Autowired
    private ResuserBiz resuserBiz;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public JsonModel logout(JsonModel jm, HttpSession session) {
        session.getAttribute(LOGINUSER);
        jm.setCode(1);
        return jm;
    }

    @RequestMapping(value = "/checkLogin", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "检查登录", notes = "检查登录")
    public JsonModel checkLoginOp(JsonModel jm, HttpSession session) {
        if (session.getAttribute(LOGINUSER) == null) {
            jm.setCode(0);
            jm.setMsg("用户没有登录");
        } else {
            jm.setCode(1);
            Resuser user = (Resuser) session.getAttribute(LOGINUSER);
            jm.setObj(user);
        }
        return jm;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "登录", notes = "登录")
    public JsonModel loginop(HttpSession session, JsonModel jm, String valcode, String username, String pwd) {
        if (valcode == null || "".equals(valcode)) {
            jm.setCode(0);
            jm.setMsg("验证码不能为空");
            return jm;
        }
        String validateCode = (String) session.getAttribute("validateCode");//在ValCodeController中生成的标准验证码，它存在session中
        if (!valcode.equalsIgnoreCase(validateCode)) {//不区分大小写
            jm.setCode(0);
            jm.setMsg("验证码输入错误....");
            return jm;
        }

        Resuser u = new Resuser();
        u.setUsername(username);
        u.setPwd(pwd);
        Resuser resuser = resuserBiz.login(u);
        if (resuser != null) {
            session.setAttribute(LOGINUSER, resuser);//目前，都是将当前用户状态信息（登录状态，购物车，菜品都在session  ）保存这个用户：在数据库中保存用户状态
            //更好地处理方案是用一个数据库/redis来保存
            jm.setCode(1);
            //再看地址
            if (session.getAttribute(YcConstants.LASTVISITEDADDR) != null) {
                jm.setUrl((String) session.getAttribute(LOGINUSER));//
            } else {
                jm.setUrl(YcConstants.HOMEPAGE);//没有历史页面，则登录成功后到首页
            }
        } else {
            jm.setCode(0);
            jm.setMsg("wrong username or password,please try again");
        }
        return jm;
    }
}
