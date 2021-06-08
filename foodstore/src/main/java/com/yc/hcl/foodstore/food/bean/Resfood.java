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
 * @create: 2021-04-29 19:22
 */
@Data
@Entity
public class Resfood implements Serializable {
    private static final long serialVersionUID = -756300758621201993L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;
    private String fname;
    private BigDecimal normprice;
    private BigDecimal realprice;
    private String detail;
    private String fphoto;
}