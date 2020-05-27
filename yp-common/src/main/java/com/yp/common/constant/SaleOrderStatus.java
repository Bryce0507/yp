package com.yp.common.constant;


public enum SaleOrderStatus {
    AUDIT(0),//审核中
    APPROVE(1),//审核通过
    PART_APPROVE(2),//审核部分通过
    REJECT(3),//审核驳回

    ;
    private Integer value;

    SaleOrderStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
