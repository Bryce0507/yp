package com.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.entity.ActivityCircle;
import com.yp.vo.wx.system.CircleCountVO;

import java.util.List;

/**
 * <p>
 * 活动圈子 服务类
 * </p>
 *
 * @author generator
 * @since 2020-02-23
 */
public interface ActivityCircleService extends IService<ActivityCircle> {

    List<CircleCountVO> queryCircleCount();
}
