package com.yp.dto.manage.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 活动库存
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动库存DTO")
@Setter
@Getter
public class ActivityReserveItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "商品编号")
    private Integer pkid;

    @ApiModelProperty(value = "商品名称")
    @NotNull
    private String productName;

    @ApiModelProperty(value = "商品名称")
    @NotNull
    private BigDecimal productPrice;

    @ApiModelProperty(value = "总数量")
    private Integer totalNumber;

    @ApiModelProperty(value = "可用数量")
    private Integer usableNumber;

}
