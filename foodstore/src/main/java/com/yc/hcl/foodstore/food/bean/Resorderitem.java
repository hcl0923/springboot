package com.yc.hcl.foodstore.food.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-29 19:23
 */
@Data
@Entity
public class Resorderitem implements Serializable {
    private static final long serialVersionUID = -2780197375765113579L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roiid;
    private Integer roid;
    private Integer fid;
    private BigDecimal dealprice;
    private Integer num;
}
