package com.yp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yp.dto.manage.sale.StaffPageDTO;
import com.yp.entity.ActivitySaleOrder;
import com.yp.entity.ActivitySaleRecord;
import com.yp.vo.manage.sale.PartakeDaySaleVO;
import com.yp.vo.manage.sale.ProductDaySaleVO;
import com.yp.vo.manage.sale.ProductSaleVO;
import com.yp.vo.manage.sale.StaffSaleVO;
import com.yp.vo.wx.sale.MySaleCountByProductVO;
import com.yp.vo.wx.sale.MySaleCountVO;
import com.yp.vo.wx.sale.StaffSaleCountVO;
import com.yp.vo.wx.shop.ShopItemCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 活动销售订单 Mapper 接口
 * </p>
 *
 * @author bryce0507
 * @since 2020-05-27
 */
@Mapper
public interface ActivitySaleOrderMapper extends BaseMapper<ActivitySaleOrder> {

    Page<ActivitySaleOrder> staffSale(Page page, @Param("staffPageDTO") StaffPageDTO staffPageDTO);
}
