package com.yc.tx.controllers;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.biz.AccountService;
import com.yc.tx.vo.AccountVO;
import com.yc.tx.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-24 20:53
 */
@Controller
@Slf4j
@Api(description = "银行账号操作接口", tags = {"账号操作接口", "控制层"})
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/openAccounts", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "开户", notes = "根据金额来完成开户操作，注意此时的金额表示要存的金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "money", value = "开户金额", required = true)
    })
    public @ResponseBody
    ResultVO openAccounts(AccountVO accountVO) {
        log.debug("用户请求开户，存入" + accountVO.getMoney());
        ResultVO rv = new ResultVO();
        try {
            Accounts a = new Accounts();
            double money = 1;
            if (accountVO.getMoney() != null && accountVO.getMoney() > 0) {
                money = accountVO.getMoney();
            }
            Integer id = accountService.openAccount(a, money);
            a.setAccountId(id);
            a.setBalance(money);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    @ApiOperation(value = "存款", notes = "根据账号，存款金额发出存款操作，返回操作完成后新的余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "存款账号", required = true),
            @ApiImplicitParam(name = "money", value = "存款金额", required = true)
    })
    public @ResponseBody
    ResultVO<Accounts> deposit(AccountVO accountVO) {
        ResultVO<Accounts> rv = new ResultVO();
        Accounts a = new Accounts();
        a.setAccountId(accountVO.getAccountId());
        try {
            a = accountService.deposite(a, accountVO.getMoney(), OpTypes.deposite.getName(), "");
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ApiOperation(value = "转账", notes = "根据账户编号及金额, 对方接收账号来完成转账操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "java.lang.Integer"),
            @ApiImplicitParam(name = "money", value = "转账金额", required = true, dataType = "java.lang.Double"),
            @ApiImplicitParam(name = "inAccountId", value = "对方接收账号", required = true, dataType = "java.lang.Integer")})
    public @ResponseBody
    ResultVO<Accounts> transfer(AccountVO accountVO) {
        Accounts inAccount = new Accounts();
        inAccount.setAccountId(accountVO.getInAccountId());
        Accounts outAccount = new Accounts();
        outAccount.setAccountId(accountVO.getAccountId());
        ResultVO<Accounts> rv = new ResultVO();
        try {
            Accounts a = accountService.transfer(inAccount, outAccount, accountVO.getMoney());
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ApiOperation(value = "取款", notes = "根据账户编号及金额来完成取款操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "账户编号", required = true, dataType = "java.lang.Integer"),
            @ApiImplicitParam(name = "money", value = "操作金额", required = true, dataType = "java.lang.Double")})
    public @ResponseBody
    ResultVO<Accounts> withdraw(AccountVO accountVO) {
        ResultVO<Accounts> rv = new ResultVO();
        Accounts a = new Accounts();
        a.setAccountId(accountVO.getAccountId());
        try {
            a = accountService.withdraw(a, accountVO.getMoney(), OpTypes.withdraw.getName(), "");
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }
}
