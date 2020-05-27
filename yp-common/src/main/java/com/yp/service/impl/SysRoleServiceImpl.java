package com.yp.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.dao.SysRoleMapper;
import com.yp.dto.RoleDTO;
import com.yp.entity.SysMenu;
import com.yp.entity.SysRole;
import com.yp.entity.SysRoleDept;
import com.yp.entity.SysRoleMenu;
import com.yp.service.ISysRoleDeptService;
import com.yp.service.ISysRoleMenuService;
import com.yp.service.ISysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private ISysRoleMenuService roleMenuService;

    @Resource
    private ISysRoleDeptService roleDeptService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRoleMenu(RoleDTO roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);

        baseMapper.insertRole(sysRole);
        Integer roleId = sysRole.getRoleId();
        //维护角色菜单
        List<SysRoleMenu> roleMenus = roleDto.getRoleMenus();
        if (CollectionUtil.isNotEmpty(roleMenus)) {
            List<SysRoleMenu> rms = roleMenus.stream().map(sysRoleMenu -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(sysRoleMenu.getMenuId());
                return roleMenu;
            }).collect(Collectors.toList());
            roleMenuService.saveBatch(rms);
        }
        // 维护角色部门权限
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRoleMenu(RoleDTO roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);

        List<SysRoleMenu> roleMenus = roleDto.getRoleMenus();
        roleMenuService.remove(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, sysRole.getRoleId()));
        roleDeptService.remove(Wrappers.<SysRoleDept>query().lambda().eq(SysRoleDept::getRoleId, sysRole.getRoleId()));

        if (CollectionUtil.isNotEmpty(roleMenus)) {
            roleMenuService.saveBatch(roleMenus);
        }
        baseMapper.updateById(sysRole);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        roleMenuService.remove(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, id));
        roleDeptService.remove(Wrappers.<SysRoleDept>query().lambda().eq(SysRoleDept::getRoleId, id));
        return super.removeById(id);
    }

    @Override
    public List<SysRole> selectRoleList(String roleName) {
        LambdaQueryWrapper<SysRole> sysRoleLambdaQueryWrapper = Wrappers.<SysRole>lambdaQuery();
        if (StrUtil.isNotEmpty(roleName)){
            sysRoleLambdaQueryWrapper.like(SysRole::getRoleName,roleName);
        }
        List<SysRole> sysRoles = baseMapper.selectList(sysRoleLambdaQueryWrapper);
        return sysRoles;
    }


    @Override
    public List<SysMenu> findMenuListByRoleId(int roleId) {
        return baseMapper.findMenuListByRoleId(roleId);
    }

    @Override
    public List<SysRole> findRolesByUserId(Integer userId) {
        return baseMapper.listRolesByUserId(userId);
    }

}
