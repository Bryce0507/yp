package com.yp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yp.common.sensitive.SensitiveInfo;
import com.yp.common.sensitive.SensitiveType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 真实姓名
     */
    @TableField("true_name")
    private String trueName;

    /**
     * 性别 0未知，1男，2女
     */
    @TableField("sex")
    private String sex;

    /**
     * 微信openId
     */
    @TableField("open_id")
    private String openId;

    /**
     * 微信昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 微信头像地址
     */
    @TableField("headimgurl")
    private String headimgurl;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;


    /**
     * 部门ID
     */
    @TableField("company_id")
    private Integer companyId;

    /**
     * 部门名称
     */
    @TableField("company")
    private String company;

    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Integer deptId;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;

    /**
     * 岗位ID
     */
    @TableField("job_id")
    private Integer jobId;

    /**
     * 岗位ID
     */
    @TableField("job_name")
    private String jobName;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 账户类型：0 客户端账户 1：管理后台账户
     */
    @TableField("user_type")
    private String userType;


    /**
     * 0-正常，1-锁定
     */
    @TableField("lock_flag")
    private String lockFlag;

    /**
     * 0-正常，1-删除
     */
    @TableField("del_flag")
    private String delFlag;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<SysRole> roleList;


}
