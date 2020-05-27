package com.yp.dto.manage.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 活动附件
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "动示例DTO")
@Setter
@Getter
public class ActivityExampleFileItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动编号")
    private Integer activityId;

    @ApiModelProperty(value = "示例描述")
    private String exampleDes;

    @ApiModelProperty(value = "文件地址")
    private String fileAddress;

    @ApiModelProperty(value = "文件状态 0：有效  1：无效")
    @TableField("status")
    private String status;

}
