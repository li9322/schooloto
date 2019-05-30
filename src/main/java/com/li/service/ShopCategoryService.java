package com.li.service;

import com.li.entity.ShopCategory;

import java.util.List;

/**
 * @ClassName: ShopCategoryService
 * @Description:
 * @author: libl
 * @date: 2019/05/30 16:01
 */
public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
}
