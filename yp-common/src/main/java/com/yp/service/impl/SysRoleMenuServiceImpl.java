package com.yp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.entity.SysRoleMenu;
import com.yp.dao.SysRoleMenuMapper;
import org.springframework.stereotype.Service;
import com.yp.service.ISysRoleMenuService;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Override
    public List<Integer> getMenuIdByUserId(Integer userId) {
        return baseMapper.getMenuIdByUserId(userId);
    }
}
