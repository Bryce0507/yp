package com.yp.dto.manage.user;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yp.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "添加用户DTO")
@Setter
@Getter
public class AddUserDTO extends Model<AddUserDTO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "真实姓名")
    private Integer userId;

    @ApiModelProperty(value = "真实姓名")
    @NotNull
    private String trueName;

    @ApiModelProperty("用户名")
    @NotNull
    private String username;

    @ApiModelProperty("用户名")
    @NotNull
    private String phone;

    @ApiModelProperty("公司ID")
    @NotNull
    private Integer companyId;

    @ApiModelProperty("部门ID")
    @NotNull
    private Integer deptId;

    @ApiModelProperty("账户类型：0 客户端账户 1：管理后台账户")
    @NotNull
    private String userType;


    @ApiModelProperty("状态：0-正常，1-锁定")
    @NotNull
    private String lockFlag;

    @ApiModelProperty("角色列表")
    @NotNull
    private List<Integer> roleList;


}
