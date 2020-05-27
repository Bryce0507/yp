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
@ApiModel(value = "设置是否隐藏DTO")
@Setter
@Getter
public class DisplayDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告ID")
    private Integer pkid;

    @ApiModelProperty(value = "是否隐藏 0：隐藏 1：不隐藏")
    private String display;
}
