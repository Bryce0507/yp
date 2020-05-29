package com.yp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.dto.manage.sale.StaffPageDTO;
import com.yp.entity.ActivitySaleOrder;
import com.yp.entity.ActivitySaleRecord;
import com.yp.vo.manage.sale.StaffSaleVO;

import java.util.List;


/**
 * <p>
 * 活动销售记单
 * </p>
 *
 * @author bryce0507
 * @since 2020-05-27
 */
public interface ActivitySaleOrderService extends IService<ActivitySaleOrder> {


    Page<ActivitySaleOrder> staffSale(Page<ActivitySaleOrder> page, StaffPageDTO staffPageDTO);

    List<StaffSaleVO> queryStaffSale(String activityId, String targetUntil);
}
