package com.yp.dto.manage.notice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 公告
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "公告DTO")
@Setter
@Getter
public class NoticePageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "显示条数")
    private int size=10;

    @ApiModelProperty(value = "当前页数")
    private int current=0;
}
