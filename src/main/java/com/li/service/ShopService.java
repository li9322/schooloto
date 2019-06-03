package com.li.service;

import com.li.dto.ShopExecution;
import com.li.entity.Shop;
import com.li.exception.ShopOperationException;

import java.io.File;
import java.io.InputStream;

/**
 * @ClassName: ShopService
 * @Description:
 * @author: libl
 * @date: 2019/05/29 15:15
 */
public interface ShopService {
    //    ShopExecution addShop(Shop shop, File shopFile);
    // 修改入参，将File类型的入参修改为InputStream,同时增加String类型的文件名称
    ShopExecution addShop(Shop shop, InputStream shopFileInputStream, String fileName);

    /**
     * @Description: 根据shopId查询商铺
     * @Param: shopId
     * @return: Shop
     * @Author: li
     */
    Shop getShopById(long shopId);

    /**
     * @Description: 编辑商铺信息
     * @Param: shop
     * @Param: shopFileInputStream
     * @Param: fileName
     * @return:ShopExecution
     * @Author: li
     */
    ShopExecution modifyShop(Shop shop, InputStream shopFileInputStream, String fileName) throws ShopOperationException;

    /**
     * @Description: 获取商铺列表. 在这一个方法中同样的会调用查询总数的DAO层方法，封装到ShopExecution中
     * @Param: shopCondition
     * @param: pageIndex 前端页面 只有第几页 第几页 定义为pageIndex
     * @param: pageSize 展示的行数
     * @throws: ShopOperationException
     * @return: ShopExecution
     * @Author: li
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) throws ShopOperationException;
}
