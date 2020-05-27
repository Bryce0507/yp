package com.yp.vo.manage.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yp.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "用户VO")
@Setter
@Getter
public class UserVO extends Model<UserVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("真实姓名")
    private String trueName;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("公司ID")
    private Integer companyId;

    @ApiModelProperty("公司名称")
    private String company;

    @ApiModelProperty("部门ID")
    private Integer deptId;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("账户类型：0 客户端账户 1：管理后台账户")
    private String userType;


    @ApiModelProperty("状态：0-正常，1-锁定")
    private String lockFlag;

    @ApiModelProperty("状态：0-正常，1-锁定")
    private String delFlag;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<SysRole> roleList;


}
