package com.yp.dto.manage.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
public class UserPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "真实姓名")
    private String trueName;

    @ApiModelProperty("用户名：手机号码")
    private String username;

    @ApiModelProperty("公司ID")
    private Integer companyId;

    @ApiModelProperty("部门ID")
    private Integer deptId;

    @ApiModelProperty("账户类型：0 客户端账户 1：管理后台账户")
    @NotNull
    private String userType;

    @ApiModelProperty(value = "显示条数")
    private int size=10;

    @ApiModelProperty(value = "当前页数")
    private int current=0;
}
