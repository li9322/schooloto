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
    ShopExecution addShop(Shop shop, InputStream shopFileInputStream,String fileName);

    /**
    *@Description: 根据shopId查询商铺
    *@Param: shopId
    *@return: Shop
    *@Author: li
    */
    Shop getShopById(long shopId);

    /**
    *@Description:  编辑商铺信息
    *@Param: shop
    *@Param: shopFileInputStream
    *@Param: fileName
    *@return:ShopExecution
    *@Author: li
    */
    ShopExecution modifyShop(Shop shop,InputStream shopFileInputStream,String fileName)throws ShopOperationException;
}
