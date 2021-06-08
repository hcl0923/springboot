package com.yc.hcl.foodstore.food.dao;

import com.yc.hcl.foodstore.food.bean.Resorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResorderDao extends JpaRepository<Resorder, Integer> {
}
