package com.yc.tx.biz;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;

import java.util.List;

public interface AccountService {
    //开户
    public Integer openAccount(Accounts accounts, double money);

    //存钱
    public Accounts deposite(Accounts accounts, double money, String optye, String tranfersid);

    //取钱
    public Accounts withdraw(Accounts accounts, double money, String optye, String transferid);

    //转账
    public Accounts transfer(Accounts accounts, Accounts outAccount, double money);

    //查看余额
    public Accounts showBalance(Accounts accounts);

    //查看日志
    public List<OpRecord> findById(Accounts account);

}
