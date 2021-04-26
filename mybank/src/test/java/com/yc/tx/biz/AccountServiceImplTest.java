package com.yc.tx.biz;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
//    @Transactional//junit中 恢复现场
    public void testOpenAccount() {
        Integer accountid = accountService.openAccount(new Accounts(), 99);
        System.out.println(accountid);
        Assert.assertNotNull(accountid);
    }

    @Test
    public void testTransfer() {
        Accounts out = new Accounts();
        out.setAccountId(6);

        Accounts inA = new Accounts();
        inA.setAccountId(7);

        this.accountService.transfer(inA, out, 5);
    }

    @Test
    public void testShowBalance() {
        Accounts a = new Accounts();
        a.setAccountId(1);
        a = this.accountService.showBalance(a);
        System.out.println(a);
    }

    @Test
    public void testDeposite() {
        Accounts a = new Accounts();
        a.setAccountId(6);
        a = this.accountService.deposite(a, 100, OpTypes.deposite.getName(), null);
        System.out.println(a);
    }

    @Test
    public void testWithdraw() {
        Accounts a = new Accounts();
        a.setAccountId(6);
        Accounts aa = accountService.withdraw(a, 999, OpTypes.withdraw.getName(), null);
        System.out.println(aa);
    }

    @Test
    public void testFindById() {

    }
}