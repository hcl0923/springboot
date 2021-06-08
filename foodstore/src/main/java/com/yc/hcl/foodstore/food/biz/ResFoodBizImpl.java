package com.yc.hcl.foodstore.food.biz;

import com.yc.hcl.foodstore.food.bean.Resfood;
import com.yc.hcl.foodstore.food.dao.ResfoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-04 19:13
 */
@Service
@Transactional
public class ResFoodBizImpl implements ResFoodBiz {

    @Autowired
    private ResfoodDao resfoodDao;

    @Override
    @Transactional(readOnly = true)
    public List<Resfood> findAll() {
        return resfoodDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Resfood findByFid(Integer fid) {
        Resfood rf = new Resfood();
        rf.setFid(fid);
        Example<Resfood> example = Example.of(rf);
        Optional<Resfood> option = resfoodDao.findOne(example);//Optional
        return option.get();
    }
}
