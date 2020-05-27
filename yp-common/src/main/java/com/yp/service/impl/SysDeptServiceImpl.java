package com.yp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.entity.SysDept;
import com.yp.dto.DeptDTO;
import com.yp.dao.SysDeptMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yp.service.ISysDeptService;
import com.yp.util.PreUtil;
import com.yp.vo.DeptTreeVo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Override
    public String selectDeptNameByDeptId(int deptId) {
        return baseMapper.selectOne(Wrappers.<SysDept>query().lambda().select(SysDept::getName).eq(SysDept::getDeptId, deptId)).getName();
    }
}
