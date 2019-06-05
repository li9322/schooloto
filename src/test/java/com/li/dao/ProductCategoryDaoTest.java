package com.li.dao;

import com.li.BaseTest;
import com.li.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProductCategoryDaoTest
 * @Description:
 * @author: libl
 * @date: 2019/06/05 9:49
 */
public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void testSelectProductCategoryList() {
        Long shopId = 2L;
        List<ProductCategory> productCategories = productCategoryDao.selectProductCategoryList(shopId);
        System.out.println(productCategories.size());
        for (ProductCategory productCategory : productCategories)
            System.out.println(productCategory);
    }

    @Test
    public void testBatchInsertProductCategory() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("ProductCategoryTest1");
        productCategory1.setProductCategoryDesc("ProductCategoryTest1-desc");
        productCategory1.setPriority(20);
        productCategory1.setCreateTime(new Date());
        productCategory1.setLastEditTime(new Date());
        productCategory1.setShopId(2L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("ProductCategoryTest2");
        productCategory2.setProductCategoryDesc("ProductCategoryTest2-desc");
        productCategory2.setPriority(22);
        productCategory2.setCreateTime(new Date());
        productCategory2.setLastEditTime(new Date());
        productCategory2.setShopId(2L);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);

        int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        System.out.println(effectNum);
    }
}
