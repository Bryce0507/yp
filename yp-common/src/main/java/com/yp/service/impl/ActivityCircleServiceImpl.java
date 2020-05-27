package com.yp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.dao.ActivityCircleMapper;
import com.yp.entity.ActivityCircle;
import com.yp.service.ActivityCircleService;
import com.yp.vo.wx.system.CircleCountVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 活动圈子 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-02-23
 */
@Service
public class ActivityCircleServiceImpl extends ServiceImpl<ActivityCircleMapper, ActivityCircle> implements ActivityCircleService {

    @Override
    public List<CircleCountVO> queryCircleCount() {
        return baseMapper.queryCircleCount();
    }
}
