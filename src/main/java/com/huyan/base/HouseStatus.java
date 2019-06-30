package com.huyan.base;

/**
 * @author 胡琰
 * @date 2019/5/5 16:24
 */
public enum HouseStatus {
    NOT_AUDITED(0),
    PASSES(1),
    RENTED(2),
    DELETED(3);
    private int value;

    HouseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
