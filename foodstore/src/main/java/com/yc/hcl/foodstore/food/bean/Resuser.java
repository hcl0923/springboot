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
 * @create: 2021-04-29 19:43
 */
@Data
@Entity
public class Resuser implements Serializable {
    private static final long serialVersionUID = -9194995583731778711L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    private String username;
    private String pwd;
    private String email;
}
