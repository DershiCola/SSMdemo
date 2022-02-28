package com.dershi.utils;

public enum DefalutNum {
    DEFALUT_NO(1),
    DEFALUT_SIZE(4);

    private final int num;

    DefalutNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
