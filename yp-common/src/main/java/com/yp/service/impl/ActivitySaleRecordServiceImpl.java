package com.yp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.dao.ActivitySaleRecordMapper;
import com.yp.dto.manage.sale.StaffPageDTO;
import com.yp.entity.ActivitySaleRecord;
import com.yp.service.ActivitySaleRecordService;
import com.yp.vo.manage.sale.PartakeDaySaleVO;
import com.yp.vo.manage.sale.ProductDaySaleVO;
import com.yp.vo.manage.sale.ProductSaleVO;
import com.yp.vo.manage.sale.StaffSaleVO;
import com.yp.vo.wx.sale.MySaleCountByProductVO;
import com.yp.vo.wx.sale.MySaleCountVO;
import com.yp.vo.wx.sale.StaffSaleCountVO;
import com.yp.vo.wx.shop.ShopItemCount;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 活动销售记录 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-02-16
 */
@Service
public class ActivitySaleRecordServiceImpl extends ServiceImpl<ActivitySaleRecordMapper, ActivitySaleRecord> implements ActivitySaleRecordService {

    @Override
    public List<StaffSaleCountVO> queryStaffSaleCountByShop(String activityId, String shopCode, String approveStatus) {
        return baseMapper.queryStaffSaleCountByShopAndActivity(activityId, shopCode,approveStatus);
    }

    @Override
    public List<MySaleCountVO> queryMySaleCountByActivity(String activityId, String userId){
        return baseMapper.queryMySaleCountByActivity(activityId, userId);
    }

    @Override
    public List<MySaleCountByProductVO> queryMySaleCountByActivityAndStatus(String activityId,String approveStatus, String userId){
        return baseMapper.queryMySaleCountByActivityAndStatus(activityId, approveStatus,userId);
    }

    @Override
    public List<ShopItemCount> queryShopCount(String activityId, String orderUntil){
        return baseMapper.queryShopCount(activityId, orderUntil);
    }
    @Override
    public ShopItemCount getShopCount(String activityId, String shopCode){
        return baseMapper.getShopCount(activityId, shopCode);
    }
    @Override
    public List<PartakeDaySaleVO> queryPartakeDaySale(String activityId, String ShopCode){
        return baseMapper.queryPartakeDaySale(activityId, ShopCode);
    }
    @Override
    public List<ProductSaleVO> queryProductSale(String activityId){
        return baseMapper.queryProductSale(activityId);
    }
    @Override
    public List<ProductDaySaleVO> queryProductDaySale(String activityId, String productId){
        return baseMapper.queryProductDaySale(activityId,productId);
    }

    @Override
    public List<StaffSaleVO> queryStaffSale(String activityId, String orderUntil){
        return baseMapper.queryStaffSale(activityId,orderUntil);
    }

    @Override
    public  Page<ActivitySaleRecord> staffSale(Page page, StaffPageDTO staffPageDTO){
        return baseMapper.staffSale(page, staffPageDTO);
    }
}
