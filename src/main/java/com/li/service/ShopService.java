package com.li.service;

import com.li.dto.ShopExecution;
import com.li.entity.Shop;

import java.io.File;

/**
 * @ClassName: ShopService
 * @Description:
 * @author: libl
 * @date: 2019/05/29 15:15
 */
public interface ShopService {
    ShopExecution addShop(Shop shop, File shopFile);
}
