package com.li.dao;

import com.li.BaseTest;
import com.li.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: ShopCategoryDaoTest
 * @Description:
 * @author: libl
 * @date: 2019/05/30 14:43
 */
public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategoryList(){

        // shopCategoryCondition 不为null的情况，查询parent_id is not null 的数据
        ShopCategory shopCategory=new ShopCategory();
        List<ShopCategory> categoryList=shopCategoryDao.queryShopCategoryList(shopCategory);
        System.out.println("categoryList--"+categoryList);
        for (ShopCategory shopCategory2:categoryList)
            System.out.println("shopCategory2--"+shopCategory2);

        // shopCategoryCondition.parent 不为null的情况

        // 查询parent=1的店铺目录
        ShopCategory child=new ShopCategory();
        ShopCategory parent=new ShopCategory();
        parent.setShopCategoryId(1L);
        child.setParent(parent);

        categoryList=shopCategoryDao.queryShopCategoryList(child);
        System.out.println("categoryList+++"+categoryList);
        for (ShopCategory shopCategory3: categoryList)
            System.out.println("shopCategory3--"+shopCategory3);

        // 查询 parent is null 的情况
        categoryList=shopCategoryDao.queryShopCategoryList(null);
        System.out.println("categoryList+++"+categoryList);
        for (ShopCategory shopCategory4: categoryList)
            System.out.println("shopCategory3--"+shopCategory4);
    }
}
