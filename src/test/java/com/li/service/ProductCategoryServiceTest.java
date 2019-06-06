package com.li.service;

import com.li.BaseTest;
import com.li.dto.ProductCategoryExecution;
import com.li.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProductCategoryServiceTest
 * @Description:
 * @author: libl
 * @date: 2019/06/05 10:10
 */
public class ProductCategoryServiceTest extends BaseTest {

    @Autowired
    ProductCategoryService productCategoryService;

    @Test
    public void testQueryProductCategoryList() {
        long shopId = 2;
        List<ProductCategory> productCategories = productCategoryService.queryProductCategoryList(shopId);
        System.out.println(productCategories.size());

        for (ProductCategory productCategory : productCategories)
            System.out.println(productCategory.toString());
    }

    @Test
    public void testAddProductCategory() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("ProductCategoryTest3");
        productCategory1.setProductCategoryDesc("ProductCategoryTest3-desc");
        productCategory1.setPriority(24);
        productCategory1.setCreateTime(new Date());
        productCategory1.setLastEditTime(new Date());
        productCategory1.setShopId(2L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("ProductCategoryTest4");
        productCategory2.setProductCategoryDesc("ProductCategoryTest4-desc");
        productCategory2.setPriority(25);
        productCategory2.setCreateTime(new Date());
        productCategory2.setLastEditTime(new Date());
        productCategory2.setShopId(2L);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);

        ProductCategoryExecution productCategoryExecution = productCategoryService.addProductCategory(productCategoryList);
        System.out.println(productCategoryExecution.getState());
        System.out.println(productCategoryExecution.getProductCategoryList().size());
    }

    @Test
    public void testDeleteProductCategory(){
        ProductCategoryExecution productCategoryExecution=productCategoryService.deleteProductCategory(4,2);
        System.out.println(productCategoryExecution.getState());
        ProductCategoryExecution productCategoryExecution2=productCategoryService.deleteProductCategory(7,2);
        System.out.println(productCategoryExecution2.getState());
    }
}
