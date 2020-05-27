package com.yp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yp.entity.SysUser;
import com.yp.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Insert("insert into sys_user (username,password,dept_id,job_id,phone,headimgurl,lock_flag) values (#{username},#{password},#{deptId},#{jobId},#{phone},#{headimgurl},#{lockFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    boolean insertUser(SysUser sysUser);

    /**
     * 分页查询用户信息（含角色）
     *
     * @param page      分页
     * @param userDTO   查询参数
     * @return list
     */
    IPage<SysUser> getUserVosPage(Page page, @Param("query") UserDTO userDTO);

}
