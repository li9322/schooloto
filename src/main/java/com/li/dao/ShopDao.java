package com.li.dao;

import com.li.entity.Area;
import com.li.entity.Shop;

import java.util.List;

public interface ShopDao {
    /**
     *
     *
     * @Title: insertShop
     *
     * @Description: 店铺注册
     *
     * @param shop
     *
     * @return: int 受影响的行数 1即为成功 -1(mybtis返回的值)失败
     */
    int insertShop(Shop shop);
}
