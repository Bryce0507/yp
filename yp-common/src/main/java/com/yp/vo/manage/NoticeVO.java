package com.yp.vo.manage;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 公告
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "公告VO")
@Setter
@Getter
public class NoticeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告ID")
    private Integer pkid;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "1：系统公告 2：业绩通过 3：业绩驳回")
    private Integer type;

    @ApiModelProperty(value = "公告内容")
    private String context;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "是否展示 ：0：不展示 1：展示")
    private String isDisplay;
}
