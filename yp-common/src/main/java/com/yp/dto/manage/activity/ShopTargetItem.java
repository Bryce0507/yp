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
import java.util.Objects;

/**
 * <p>
 * 门店指标
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "门店指标DTO")
@Setter
@Getter
public class ShopTargetItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店编号")
    @NotNull
    private String shopCode;

    @TableField("target")
    private Integer target;

}
