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
@ApiModel(value = "活动附件DTO")
@Setter
@Getter
public class ActivityFileItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动编号")
    private Integer activityId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件类型 0:文档  1:视频")
    private String fileType;

    @ApiModelProperty(value = "文件后缀")
    private String filePostfix;

    @ApiModelProperty(value = "文件地址")
    private String fileAddress;

}
