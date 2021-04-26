package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-17 14:56
 */
@Repository
public class OpRecordDaoImpl implements OpRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveOpRecord(OpRecord opRecord) {
        String sql = "insert into oprecord(accountid,opmoney,optime,optype,transferid) values(?,?,?,?,?)";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, opRecord.getAccountid());
            pstmt.setDouble(2, opRecord.getOpmoney());
            pstmt.setTimestamp(3, opRecord.getOptime());
            pstmt.setString(4, opRecord.getOptype());
            pstmt.setString(5, opRecord.getTransferid());
            return pstmt;
        });
    }

    @Override
    public List<OpRecord> findAll() {
        String sql = "select * from oprecord";
        List<OpRecord> list = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            OpRecord a = new OpRecord();
            a.setId(resultSet.getInt("id"));
            a.setAccountid(resultSet.getInt("accountid"));
            a.setOpmoney(resultSet.getDouble("opmoney"));
            a.setOptime(resultSet.getTimestamp("optime"));
            a.setOptype(resultSet.getString("optype"));
            a.setTransferid(resultSet.getString("transferid"));
            return a;
        });
        return list;
    }

    @Override
    public List<OpRecord> findByAccountId(int accountid) {
        String sql = "select * from oprecord where accountid =?";
        List<OpRecord> list = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            OpRecord a = new OpRecord();
            a.setId(resultSet.getInt("id"));
            a.setAccountid(resultSet.getInt("accountid"));
            a.setOpmoney(resultSet.getDouble("opmoney"));
            a.setOptime(resultSet.getTimestamp("optime"));
            a.setOptype(resultSet.getString("optype"));
            a.setTransferid(resultSet.getString("transferid"));
            return a;
        }, accountid);//多传一个参数
        return list;
    }
}
