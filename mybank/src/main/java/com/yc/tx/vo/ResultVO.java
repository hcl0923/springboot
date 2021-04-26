package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-04-24 20:40
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -5812196032135386958L;
    private Integer code;
    private T data;
    private String msg;
}
