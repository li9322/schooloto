package com.li.dao;

import com.li.entity.Area;
import com.li.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * @param shop
     * @Title: insertShop
     * @Description: 店铺注册
     * @return: int 受影响的行数 1即为成功 -1(mybtis返回的值)失败
     */
    int insertShop(Shop shop);

    /**
     * @param shop
     * @Title: updateShop
     * @Description: 更新店铺
     * @return: int
     */
    int updateShop(Shop shop);

    /**
     * @Description:带有分页功能的查询商铺列表 。
     * 可输入的查询条件：商铺名（要求模糊查询） 区域Id 商铺状态 商铺类别 owner
     * (注意在sqlmapper中按照前端入参拼装不同的查询语句)
     * @Param:shopCondition
     * @Param:rowIndex从第几行开始取
     * @Param:pageSize返回多少行数据（页面上的数据量）
     *        比如 rowIndex为1,pageSize为5 即为 从第一行开始取，取5行数据
     * @return:List<Shop>
     * @Author: li
     */
    List<Shop> selectShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * @Description:按照条件查询 符合前台传入的条件的商铺的总数
     * @Param:shopCondition
     * @return:int
     * @Author: li
     */
    int selectShopCount(@Param("shopCondition") Shop shopCondition);
    
    /**
    *@Description: 根据shopId查询shop
    *@Param: shopId
    *@return: Shop
    *@Author: li
    */
    Shop selectShopById(long shopId);
}
