package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-15 22:28
 */
@Repository
public class AccountsDaoImpl implements AccountsDao {
    //在dao中要使用 JdbcTemple的模板对象，这个对象要通过datasource创建。
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveAccount(Accounts accounts) {
        String sql = "insert into accounts(balance) values (?)";
        //利用KeyHolder来获取新增的这条数据的id
        KeyHolder keyHolder = new GeneratedKeyHolder();//生成键的保存键
        //connection由spring调用的时候注入
        jdbcTemplate.update(connection -> {//lambda表达式
            //要指定表中是哪个列为主键生成列
            PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"accountid"});
            pstmt.setDouble(1, accounts.getBalance());
            return pstmt;
        }, keyHolder);
        //用匿名内部类书写
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"accountid"});
//                pstmt.setDouble(1, accounts.getBalance());
//                return pstmt;
//            }
//        }, keyHolder);
//        keyHolder.getKey()now contains the generated key
        return keyHolder.getKey().intValue();
    }


    @Override
    public Accounts updateAccount(Accounts account) {
        String sql = "update accounts set balance =? where accountid = ? ";
        this.jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
        return account;
    }

    @Override
    public Accounts findAccount(int accountid) {
        String sql = "select * from accounts where accountid= ? ";
        return this.jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountId(resultSet.getInt("accountid"));
                    a.setBalance(resultSet.getDouble("balance"));
                    return a;
                }, accountid);
    }


    @Override
    public List<Accounts> findAll() {
        String sql = "select * from accounts";

        List<Accounts> list = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            System.out.println("当前取得是第" + rowNum + "行的数据");
            Accounts a = new Accounts();
            a.setAccountId(resultSet.getInt("accountid"));
            a.setBalance(resultSet.getDouble("balance"));
            return a;
        });
//        List<Accounts> list = this.jdbcTemplate.query(sql, new RowMapper<Accounts>() {
//            @Override
//            public Accounts mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//                System.out.println("当前取得是第" + rowNum + "行的数据");
//                Accounts a = new Accounts();
//                a.setAccountId(resultSet.getInt("accountid"));
//                a.setBalance(resultSet.getDouble("balance"));
//                return a;
//            }
//        });
        return list;
    }

    @Override
    public void delete(int accountid) {
        String sql = "delete from accounts where accountid=?";
        this.jdbcTemplate.update(sql, accountid);
    }
}
