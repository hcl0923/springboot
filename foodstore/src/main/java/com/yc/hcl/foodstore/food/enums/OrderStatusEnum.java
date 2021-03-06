package com.yc.hcl.foodstore.food.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"), FINISHED(1, "已完结"), CANSEL(2, "已取消   已取消");
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
