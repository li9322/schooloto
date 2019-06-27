package com.li.dao;

import com.dyuproject.protostuff.LimitedInputStream;
import com.li.BaseTest;
import com.li.entity.Product;
import com.li.entity.ProductCategory;
import com.li.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void testA_InsertProduct() {

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(7l);

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        Shop shop = new Shop();
        shop.setShopId(3l);

        Product product = new Product();
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

        int effectNum = productDao.insertProduct(product);
        System.out.println(effectNum);

    }

    @Test
    public void testB_UpdateProduct() {

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(7l);

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        Shop shop = new Shop();
        shop.setShopId(3l);

        Product product = new Product();
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
        int effectNum = productDao.updateProduct(product);
        System.out.println(effectNum);

    }

    @Test
    public void testC_selectProductListAndCount() {
        int rowIndex = 0;
        int pageSize = 2;
        List<Product> productList = new ArrayList<>();
        int effectNum = 0;

        Shop shop = new Shop();
        shop.setShopId(3L);

        Product productCondition = new Product();
        productCondition.setShop(shop);

        productList = productDao.selectProductList(productCondition, rowIndex, pageSize);
        System.out.println(productList.size());

        effectNum = productDao.selectCountProduct(productCondition);
        System.out.println(effectNum);

        System.out.println("-------------------------------------");

        Shop shop2 = new Shop();
        shop2.setShopId(2l);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(7l);

        Product productCondition2 = new Product();
        productCondition2.setShop(shop2);
        productCondition2.setProductCategory(productCategory);
        productCondition2.setProductName("test_product2");

        productList = productDao.selectProductList(productCondition2, rowIndex, pageSize);
        System.out.println(productList.size());

        effectNum = productDao.selectCountProduct(productCondition2);
        System.out.println(effectNum);

    }

}
