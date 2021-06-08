package com.yc.hcl.foodstore.food.biz;

import com.yc.hcl.foodstore.food.bean.Resuser;
import com.yc.hcl.foodstore.food.dao.ResuserDao;
import com.yc.hcl.foodstore.food.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-04 19:13
 */
@Service
public class ResuserBizImpl implements ResuserBiz {

    @Autowired
    private ResuserDao resuserDao;

    @Override
    @Transactional(readOnly = true)
    public Resuser login(Resuser user) {
        user.setPwd(Encrypt.md5(user.getPwd()));
        Example<Resuser> example = Example.of(user);
        Optional<Resuser> option = resuserDao.findOne(example);//业务层完成原始密码的操作
        return option.orElseGet(() -> {
            return null;
        });
    }
}
