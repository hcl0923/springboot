package com.yc.hcl.foodstore.food.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-29 19:22
 */
@Data
@Entity
public class Resorder implements Serializable {
    private static final long serialVersionUID = 3595218409811797453L;
    private String address;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roid;
    private Integer userid;
    private String tel;
    private Timestamp ordertime;
    private Timestamp deliverytime;
    private String ps;
    private Integer status;

    @Transient
    private String deliverytimeString;

    public Timestamp getDeliverytime() {
        Date d = null;
        if (deliverytimeString != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                d = df.parse(deliverytimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            d = new Date();
        }
        deliverytime = new Timestamp(d.getTime());
        return this.deliverytime;
    }
}
