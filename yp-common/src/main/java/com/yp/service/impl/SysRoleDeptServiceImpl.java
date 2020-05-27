package com.yp.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.entity.SysRoleDept;
import com.yp.dao.SysRoleDeptMapper;
import org.springframework.stereotype.Service;
import com.yp.service.ISysRoleDeptService;

import java.util.List;

/**
 * <p>
 * 角色与部门对应关系 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {


    @Override
    public List<SysRoleDept> getRoleDeptIds(int roleId) {
        return baseMapper.selectList(Wrappers.<SysRoleDept>lambdaQuery().select(SysRoleDept::getDeptId).eq(SysRoleDept::getRoleId,roleId));
    }
}
