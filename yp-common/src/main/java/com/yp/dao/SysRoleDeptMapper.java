package com.yp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yp.entity.SysRoleDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色与部门对应关系 Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Mapper
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDept> {

}
