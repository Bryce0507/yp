package com.yp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.dto.manage.activity.ActivityDTO;
import com.yp.dto.manage.user.AddUserDTO;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.entity.Activity;
import com.yp.vo.manage.activity.ActivityDetailVO;

/**
 * <p>
 * 活动表 服务类
 * </p>
 *
 * @author generator
 * @since 2020-02-16
 */
public interface ActivityService extends IService<Activity> {
    /**
     * 添加活动
     * @param activityDTO
     * @return
     */
    boolean insertActivity(ActivityDTO activityDTO);

    /**
     * 活动详情
     * @return
     */
    ActivityDetailVO activityDetail(ActivityIdDTO activityIdDTO);

    /**
     * 修改活动
     * @param activityDTO
     * @return
     */
    boolean updateActivity(ActivityDTO activityDTO);

}
