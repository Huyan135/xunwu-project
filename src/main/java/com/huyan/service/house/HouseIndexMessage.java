package com.huyan.service.house;

/**
 * @author 胡琰
 * @date 2019/4/29 20:50
 */
public class HouseIndexMessage {

    private Long houseId;
    private String operation;
    private int retry = 0;

    public HouseIndexMessage() {
    }

    public HouseIndexMessage(Long houseId, String operation, int retry){
        this.houseId = houseId;
        this.operation = operation;
        this.retry = retry;
    }
}
