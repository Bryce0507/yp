package com.yp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yp.entity.ActivityCircle;
import com.yp.vo.wx.sale.MySaleCountByProductVO;
import com.yp.vo.wx.system.CircleCountVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 活动圈子 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2020-02-23
 */
public interface ActivityCircleMapper extends BaseMapper<ActivityCircle> {
    @Select("select activity_id activityId,COUNT(DISTINCT user_id) circleCount from activity_circle GROUP BY activity_id")
    List<CircleCountVO> queryCircleCount();
}
