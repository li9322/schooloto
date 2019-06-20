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
 * @date: 2019/06/20 13:40
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTest extends BaseTest {

    @Autowired
    ProductDao productDao;

    @Test
    public void testA_InsertProduct(){

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductCategoryId(7l);

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        Shop shop=new Shop();
        shop.setShopId(3l);

        Product product=new Product();
        product.setProductName("test_product4");
        product.setProductDesc("product4 desc");
        product.setImgAddr("/aaa4/bbb4");
        product.setNormalPrice("21");
        product.setPromotionPrice("7");
        product.setPriority(34);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(1);
        product.setProductCategory(productCategory);
        product.setShop(shop);

        int effectNum=productDao.insertProduct(product);
        System.out.println(effectNum);

    }

    @Test
    public void testB_UpdateProduct(){

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductCategoryId(7l);

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        Shop shop=new Shop();
        shop.setShopId(3l);

        Product product=new Product();
        product.setProductName("test_product4update");
        product.setProductDesc("product4update desc");
        product.setImgAddr("/aaa4/bbb4");
        product.setNormalPrice("210");
        product.setPromotionPrice("6");
        product.setPriority(35);
        product.setLastEditTime(new Date());
        product.setEnableStatus(1);
        product.setProductCategory(productCategory);
        product.setShop(shop);

        product.setProductId(5l);
        int effectNum=productDao.updateProduct(product);
        System.out.println(effectNum);

    }
}
