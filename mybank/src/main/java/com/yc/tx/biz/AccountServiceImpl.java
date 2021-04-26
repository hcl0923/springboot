package com.yc.tx.biz;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.AccountsDao;
import com.yc.tx.dao.OpRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-17 17:00
 */

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountsDao accountsDao;

    @Autowired
    private OpRecordDao opRecordDao;

    @Override
    public Integer openAccount(Accounts account, double money) {
        //开户时存一条accounts记录
        account.setBalance(money);
        int accountid = accountsDao.saveAccount(account);
        //开户时的日志
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(accountid);
        opRecord.setOpmoney(money);
        opRecord.setOptype(OpTypes.deposite.getName());//用枚举做这个值约束值，不容易出错
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));//这里是一个TImeStamp new Date().getTime()取得一个Long
        opRecord.setTransferid(" ");
        opRecordDao.saveOpRecord(opRecord);
        return accountid;
    }

    @Override
    public Accounts deposite(Accounts account, double money, String optye, String transferid) {
        Accounts a = this.showBalance(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optye);                                   //用枚举做这个值（约束值），不容易出错
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));//这里是一个Timestamp
        if (transferid == null) {
            opRecord.setTransferid(" ");
        } else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);

        a.setBalance(a.getBalance() + money);
        a = accountsDao.updateAccount(a);
        return a;
    }

    @Override
    @Transactional
    public Accounts withdraw(Accounts account, double money, String optye, String transferid) {
        Accounts a = this.showBalance(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optye);//用枚举做这个值(约束值)，不容易出错
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        if (transferid == null) {
            opRecord.setTransferid(" ");
        } else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);
        a.setBalance(a.getBalance() - money);
        a = accountsDao.updateAccount(a);
        return a;
    }

    @Override
    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money) {
        String uid = UUID.randomUUID().toString();//转账流水
        this.deposite(inAccount, money, OpTypes.deposite.getName(), uid);
        Accounts newAccounts = this.withdraw(outAccount, money, OpTypes.withdraw.getName(), uid);
        return newAccounts;
    }

    @Override
    @Transactional(readOnly = true)
    public Accounts showBalance(Accounts account) {
        return accountsDao.findAccount(account.getAccountId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpRecord> findById(Accounts account) {
        return opRecordDao.findByAccountId(account.getAccountId());
    }
}
