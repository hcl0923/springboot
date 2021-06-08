package com.yc.hcl.foodstore.food.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-29 19:22
 */
@Entity//实体类
@Data
public class Resadmin implements Serializable {
    private static final long serialVersionUID = -995102265506171543L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer raid;//用引用类型
    private String raname;
    private String rapwd;
}
