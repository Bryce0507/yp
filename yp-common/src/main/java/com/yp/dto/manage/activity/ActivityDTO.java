package com.yp.dto.manage.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动DTO")
@Setter
@Getter
public class ActivityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动ID")
    @NotNull
    private String pkid;

    @ApiModelProperty(value = "活动名称")
    @NotNull
    private String activityName;

    @ApiModelProperty(value = "目标描述")
    @NotNull
    private String activityDesc;

    @ApiModelProperty(value = "活动内容")
    @NotNull
    private String activityContent;

    @ApiModelProperty(value = "活动banner")
    @NotNull
    private String activityBanner;

    @ApiModelProperty(value = "活动示例不能为空")
    @Size(min = 1,message = "示例不能为空")
    private List<ActivityExampleFileItem> exampleFiles;

    @ApiModelProperty(value = "附件")
    private List<ActivityFileItem> files;

    @ApiModelProperty(value = "是否设置库存 0:否 1：是")
    private String isSetReserve;

    @ApiModelProperty(value = "活动库存不能为空，设置库存时库存数量不能为空")
    private List<ActivityReserveItem> reserves;

    @ApiModelProperty(value = "设置指标  0:否 1：是")
    private String isSetTarget;

    @ApiModelProperty(value = "指标数量")
    private Integer targetNumber;

    @ApiModelProperty(value = "指标单位 0:台 1：元")
    private String targetUntil;

    @ApiModelProperty(value = "设置门店指标  0:否 1：是")
    private String isSetShopTarget;

    @ApiModelProperty(value = "参与门店，设置门店指标时，参与门店指标不能为空")
    private List<ShopTargetItem> shopTargets;

    @ApiModelProperty(value = "是否公开 0:是 1：否")
    private String isPublic="0";

    @ApiModelProperty(value = "分工安排")
    private String divisionPlan;

    @ApiModelProperty(value = "相关支持")
    private String relatedSupport;

    @ApiModelProperty(value = "奖励方案类型： 0：人工 1：自动")
    private String rewardPlanType;

    @ApiModelProperty(value = "奖励方案为门店时不能为空")
    private List<ActivityAwardRuleItem> awardRules;

    @ApiModelProperty(value = "开始日期")
    private Date startTime;

    @ApiModelProperty(value = "结束日期")
    private Date endTime;
}
