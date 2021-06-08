package com.yc.hcl.foodstore.food.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: springboot
 * @description:
 * @author: 作者
 * @create: 2021-05-05 10:31
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//排除空属性在生成的json字符串中
public class JsonModel implements Serializable {
    private static final long serialVersionUID = -576545716809606195L;
    private Integer code;
    private String msg;
    private Object obj;
    private String url;

}
