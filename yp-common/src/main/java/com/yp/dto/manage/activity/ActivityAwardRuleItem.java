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
 * 活动奖励规则
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动奖励规则DTO")
@Setter
@Getter
public class ActivityAwardRuleItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "奖励ID")
    private Integer pkid;

    @ApiModelProperty(value = "奖项名称")
    @NotNull
    private String awardName;

    @ApiModelProperty(value = "奖励")
    @NotNull
    private String award;

    @ApiModelProperty(value = "排名")
    @NotNull
    private Integer top;

}
