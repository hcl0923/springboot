package com.yc.hcl.foodstore.food.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-29 19:46
 */
@Data
@Entity
public class Resorderitemtemp implements Serializable {
    private static final long serialVersionUID = 677494118213388241L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roitid;
    private Integer fid;
    private Integer num;
    private Timestamp quittime;
    private Integer userid;
}
