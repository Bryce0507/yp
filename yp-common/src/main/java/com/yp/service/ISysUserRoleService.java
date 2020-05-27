package com.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    /**
     * 根据用户id查询用户角色关系
     * @param userId
     * @return
     */
    List<SysUserRole> selectUserRoleListByUserId(Integer userId);
}
