package com.yp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 活动销售记录 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2020-02-16
 */
@Mapper
public interface ActivitySaleRecordMapper extends BaseMapper<ActivitySaleRecord> {

    @Select("SELECT user_id userId,COUNT(*) quantity,SUM(product_price) saleMoney FROM `activity_sale_record` where activity_id=#{activityId} AND shop_code=#{shopCode}  AND approve_status=#{approveStatus} GROUP BY user_id;")
    List<StaffSaleCountVO> queryStaffSaleCountByShopAndActivity(String activityId, String shopCode, String approveStatus);

    @Select("SELECT approve_status approveStatus,COUNT(*) quantity,SUM(product_price) saleMoney FROM `activity_sale_record` where activity_id=#{activityId} AND user_id=#{userId} GROUP BY approve_status")
    List<MySaleCountVO> queryMySaleCountByActivity(String activityId, String userId);


    @Select("SELECT DATE_FORMAT(sale_time,\"%Y-%m-%d\") saleTime,product_id productId,approve_status approveStatus,COUNT(*) quantity,SUM(product_price) saleMoney FROM `activity_sale_record` where activity_id=#{activityId} AND approve_status=#{approveStatus} AND user_id=#{userId} GROUP BY DATE_FORMAT(sale_time,\"%Y-%m-%d\"),product_id")
    List<MySaleCountByProductVO> queryMySaleCountByActivityAndStatus(String activityId,String approveStatus, String userId);

    @Select("<script>"+"SELECT shop_code shopCode,COUNT(*) quantity,SUM(product_price) saleMoney FROM `activity_sale_record` where activity_id=#{activityId} and approve_status=1 GROUP BY shop_code"
             +"<if test='orderUntil!=null and orderUntil == 0 '>"
                +" order by quantity desc"
            + "</if>"
            +"<if test='orderUntil!=null and orderUntil == 1 '>"
            +" order by saleMoney desc"
            + "</if>"
            +"</script>")
    List<ShopItemCount> queryShopCount( @Param("activityId")String activityId , @Param("orderUntil")String orderUntil);

    @Select("<script>"+"SELECT shop_code shopCode,COUNT(*) quantity,SUM(product_price) saleMoney FROM `activity_sale_record` where activity_id=#{activityId} and shop_code=#{shopCode} and approve_status=1 GROUP BY shop_code"
            +"</script>")
    ShopItemCount getShopCount( @Param("activityId")String activityId , @Param("shopCode")String shopCode);

    @Select("select DATE_FORMAT(sale_time,\"%Y-%m-%d\") saleTime,COUNT(*) quantity,SUM(product_price) saleMoney from activity_sale_record where activity_id=#{activityId} AND shop_code=#{ShopCode} and approve_status=1 GROUP BY DATE_FORMAT(sale_time,\"%Y-%m-%d\")")
    List<PartakeDaySaleVO> queryPartakeDaySale(String activityId, String ShopCode);

    @Select("select product_id productId,COUNT(*) quantity,SUM(product_price) saleMoney from activity_sale_record where activity_id=#{activityId} and approve_status=1 GROUP BY product_id")
    List<ProductSaleVO> queryProductSale(String activityId);

    @Select("select  DATE_FORMAT(sale_time,\"%Y-%m-%d\") saleTime,COUNT(*) quantity,SUM(product_price) saleMoney from activity_sale_record where activity_id=#{activityId} AND product_id=#{productId} and approve_status=1 GROUP BY  DATE_FORMAT(sale_time,\"%Y-%m-%d\")")
    List<ProductDaySaleVO> queryProductDaySale(String activityId, String productId);

    @Select("<script>"+"select user_id userId,SUM(case when approve_status=1 then 1 else 0 end) quantity,SUM(case when approve_status=0 then 1 else 0 end) approveQuantity ," +
            "SUM(case when approve_status=1 then product_price else 0 end) saleMoney,SUM(case when approve_status=0 then product_price else 0 end) approveSaleMoney" +
            " from activity_sale_record where activity_id=#{activityId} GROUP BY user_id"
            +"<if test='orderUntil!=null and orderUntil == 0 '>"
            +" order by quantity desc"
            + "</if>"
            +"<if test='orderUntil!=null and orderUntil == 1 '>"
            +" order by saleMoney desc"
            + "</if>"
            +"</script>")
    List<StaffSaleVO> queryStaffSale(@Param("activityId") String activityId, @Param("orderUntil")String orderUntil);

    Page<ActivitySaleRecord> staffSale(Page page, @Param("staffPageDTO")StaffPageDTO staffPageDTO);
}
