package com.yp.vo.manage.sale;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zxb
 * @date 2020/5/27 17:00
 */
@Data
public class ProductDetailVO {

    @ApiModelProperty(value = "活动产品销售记录ID")
    @JSONField(name = "pkid")
    private Integer saleRecordId;

    @ApiModelProperty(value = "产品id")
    private Integer productId;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "产品售价")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "审核状态 0:未审核 1：通过 2：驳回")
    private Integer approveStatus;

    @ApiModelProperty(value = "驳回原因")
    private String overruleReason;


}
