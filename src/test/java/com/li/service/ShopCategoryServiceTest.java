package com.li.service;

import com.li.BaseTest;
import com.li.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: ShopCategoryServiceTest
 * @Description:
 * @author: libl
 * @date: 2019/05/30 16:02
 */
public class ShopCategoryServiceTest extends BaseTest {

    @Autowired
    ShopCategoryService shopCategoryService;

    @Test
    public void testQueryShopCategory(){
        ShopCategory shopCategory=new ShopCategory();
        List<ShopCategory> shopCategories=shopCategoryService.getShopCategoryList(shopCategory);
        System.out.println(shopCategories.size());
        for (ShopCategory shopCategory2:shopCategories)
            System.out.println(shopCategory2);
    }
}
