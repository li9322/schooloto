package com.li.dao;

import com.li.BaseTest;
import com.li.entity.Product;
import com.li.entity.ProductCategory;
import com.li.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @ClassName: ProductDaoTest
 * @Description:
 * @author: libl
 * @date: 2019/06/06 16:46
 */
public class ProductDaoTest extends BaseTest {

    @Autowired
    ProductDao productDao;

    @Test
    public void testInsertProduct() {

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(7l);

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        Shop shop = new Shop();
        shop.setShopId(2l);

        Product product = new Product();
        product.setProductName("test_product2");
        product.setProductDesc("product2 desc");
        product.setImgAddr("/aaa2/bbb2");
        product.setNormalPrice("20");
        product.setPromotionPrice("8");
        product.setPriority(31);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(1);
        product.setProductCategory(productCategory);
        product.setShop(shop);

        int effectNum = productDao.insertProduct(product);
        System.out.println(effectNum);

    }

    @Test
    public void testUpdateProductCategory2Null(){
        long productCategoryId=7l;
        long shopId=2l;
        int effectNum=productDao.updateProductCategory2Null(productCategoryId,shopId);
        System.out.println(effectNum);
        productCategoryId=5l;
        effectNum=productDao.updateProductCategory2Null(productCategoryId,shopId);
        System.out.println(effectNum);
    }
}
