package com.yc.tx.bean;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-04-17 14:15
 */

public enum OpTypes {
    deposite("deposite", 1), withdraw("withdraw", 2), transfer("transfer", 3);
    private String name;
    private int index;

    //构造方法
    private OpTypes(String name, int index) {
        this.name = name;
        this.index = index;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.index + "-" + this.name;
    }

    public String getName() {
        return this.name;
    }
}
