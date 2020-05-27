package com.yp.vo.wx.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "我的业绩统计-视图")
public class MySaleCountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "审批状态：0：待审核 1：通过 2：驳回")
    private String approveStatus;

    @ApiModelProperty(value = "台数")
    private Integer quantity;

    @ApiModelProperty(value = "金额")
    private BigDecimal saleMoney;

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }
}
