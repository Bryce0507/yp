package com.yp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.dto.manage.user.AddUserDTO;
import com.yp.dto.manage.user.UserPageDTO;
import com.yp.entity.SysUser;
import com.yp.dto.UserDTO;
import com.yp.dto.wx.WxBindDTO;
import com.yp.vo.PageBean;
import com.yp.vo.manage.user.UserVO;

import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 分页查询用户信息（含有角色信息）
     * @param userPageDTO 分页参数列表
     * @return
     */
    PageBean<UserVO> getUsersWithRolePage(UserPageDTO userPageDTO);

    /**
     * 保存用户以及角色部门等信息
     * @param userDto
     * @return
     */
    boolean insertUser(AddUserDTO userDto);

    /**
     * 更新用户以及角色部门等信息
     * @param userDto
     * @return
     */
    boolean updateUser(AddUserDTO userDto);

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    boolean removeUser(Integer userId);

    /**
     * 重置密码
     * @param userId
     * @return
     */
    boolean restPass(Integer userId);

    /**
     * 通过用户名查找用户个人信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUserInfoName(String username);

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    Set<String> findPermsByUserId(Integer userId);

    /**
     * 通过用户id查询角色集合
     * @param userId
     * @return
     */
    Set<String> findRoleIdByUserId(Integer userId);

    /**
     * 账户密码登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);
    /**
     * 修改用户信息
     * @param sysUser
     * @return
     */
    boolean updateUserInfo(SysUser sysUser);

    /**
     * 通过用户去查找用户(用户名)
     * @param sysUser
     * @return
     */
    SysUser findSecurityUserByUser(SysUser sysUser);


    /**
     * 第三方账户绑定
     * @param wxBindDTO
     * @return
     */
    boolean bind(WxBindDTO wxBindDTO);


}
