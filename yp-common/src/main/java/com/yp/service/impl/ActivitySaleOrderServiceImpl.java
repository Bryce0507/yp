package com.yp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.dao.ActivitySaleOrderMapper;
import com.yp.dto.manage.sale.StaffPageDTO;
import com.yp.entity.ActivitySaleOrder;
import com.yp.service.ActivitySaleOrderService;
import com.yp.vo.manage.sale.StaffSaleVO;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 活动销售记单
 * </p>
 *
 * @author bryce0507
 * @since 2020-05-27
 */
@Service
public class ActivitySaleOrderServiceImpl extends ServiceImpl<ActivitySaleOrderMapper, ActivitySaleOrder> implements ActivitySaleOrderService {


    @Override
    public Page<ActivitySaleOrder> staffSale(Page<ActivitySaleOrder> page, StaffPageDTO staffPageDTO) {
        return baseMapper.staffSale(page, staffPageDTO);
    }

    @Override
    public List<StaffSaleVO> queryStaffSale(String activityId, String targetUntil) {
        return baseMapper.queryStaffSale(activityId, targetUntil);
    }
}
