package com.li.service.impl;

import com.li.dao.ShopCategoryDao;
import com.li.entity.ShopCategory;
import com.li.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ShopCategoryServiceImpl
 * @Description:
 * @author: libl
 * @date: 2019/05/30 16:01
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        return shopCategoryDao.queryShopCategoryList(shopCategory);
    }
}
