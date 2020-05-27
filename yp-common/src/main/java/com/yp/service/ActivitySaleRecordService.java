package com.yp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.dto.manage.sale.StaffPageDTO;
import com.yp.entity.ActivitySaleRecord;
import com.yp.vo.manage.sale.PartakeDaySaleVO;
import com.yp.vo.manage.sale.ProductDaySaleVO;
import com.yp.vo.manage.sale.ProductSaleVO;
import com.yp.vo.manage.sale.StaffSaleVO;
import com.yp.vo.wx.sale.MySaleCountByProductVO;
import com.yp.vo.wx.sale.MySaleCountVO;
import com.yp.vo.wx.sale.StaffSaleCountVO;
import com.yp.vo.wx.shop.ShopItemCount;

import java.util.List;

/**
 * <p>
 * 活动销售记录 服务类
 * </p>
 *
 * @author generator
 * @since 2020-02-16
 */
public interface ActivitySaleRecordService extends IService<ActivitySaleRecord> {

    /**
     * 查询门店业绩汇总数据
     * @param activityId
     * @param shopCode
     * @param approveStatus
     * @return
     */
    List<StaffSaleCountVO> queryStaffSaleCountByShop(String activityId, String shopCode, String approveStatus);

    /**
     * 查询个人业绩汇总数据
     * @param activityId
     * @param userId
     * @return
     */
    List<MySaleCountVO> queryMySaleCountByActivity(String activityId, String userId);

    /**
     * 查询个人业-状态绩汇总数据
     * @param activityId
     * @param userId
     * @return
     */
    List<MySaleCountByProductVO> queryMySaleCountByActivityAndStatus(String activityId,String approveStatus, String userId);

    /**
     * 查询参与活动门店的业绩统计
     * @param activityId
     * @param orderUntil 0:按台数排序  1：按金额排序
     * @return
     */
    List<ShopItemCount> queryShopCount(String activityId, String orderUntil);

    /**
     * 查询门店业绩
     * @param activityId
     * @param shopCode 门店编号
     * @return
     */
    ShopItemCount getShopCount(String activityId, String shopCode);

    List<PartakeDaySaleVO> queryPartakeDaySale(String activityId, String ShopCode);

    List<ProductSaleVO> queryProductSale(String activityId);

    List<ProductDaySaleVO> queryProductDaySale(String activityId, String productId);

    List<StaffSaleVO> queryStaffSale(String activityId, String orderUntil);

    Page<ActivitySaleRecord> staffSale(Page page, StaffPageDTO staffPageDTO);

}
