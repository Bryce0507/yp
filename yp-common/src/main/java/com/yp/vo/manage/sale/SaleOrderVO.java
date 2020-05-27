package com.yp.vo.manage.sale;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zxb
 * @date 2020/5/27 16:57
 */
@Data
public class SaleOrderVO {
    @ApiModelProperty(value = "主键")
    private Integer pkid;

    @ApiModelProperty(value = "销售单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "活动编号")
    private Integer activityId;

    @ApiModelProperty(value = "门店编号")
    private Integer shopCode;

    @ApiModelProperty(value = "销售单价格")
    private BigDecimal saleOrderPrice;

    @ApiModelProperty(value = "门店名称")
    private String shopName;

    @ApiModelProperty(value = "用户编号")
    private Integer userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "车牌号")
    private String carNumber;

    @ApiModelProperty(value = "销售单状态 0:审核中 1：通过 2：部分通过 3：已驳回")
    private Integer saleOrderStatus;

    @ApiModelProperty(value = "业绩凭证")
    private List<String> files;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "销售时间")
    private Date saleTime;

    @ApiModelProperty(value = "产品信息")
    private List<ProductDetailVO> productDetailList;
}
