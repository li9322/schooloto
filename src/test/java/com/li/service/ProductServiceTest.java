package com.li.service;

import com.li.BaseTest;
import com.li.dto.ImageHolder;
import com.li.dto.ProductExecution;
import com.li.entity.Product;
import com.li.entity.ProductCategory;
import com.li.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProductServiceTest
 * @Description:
 * @author: libl
 * @date: 2019/06/18 14:47
 */
public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws Exception{

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        ProductCategory productCategory=new ProductCategory();

        productCategory.setProductCategoryId(2l);

        Shop shop=new Shop();
        shop.setShopId(3l);

        // 构造Product
        Product product=new Product();
        product.setProductName("test_product1");
        product.setProductDesc("product1 desc");

        product.setNormalPrice("10");
        product.setPromotionPrice("8");
        product.setPriority(66);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(1);
        product.setProductCategory(productCategory);
        product.setShop(shop);

        // 构造 商品图片
        File productFile=new File("D:/worktest/schooloto/image/20190618151801.jpg");
        InputStream ins=new FileInputStream(productFile);
        ImageHolder imageHolder=new ImageHolder(ins,productFile.getName());

        // 构造商品详情图片
        List<ImageHolder> prodImgDetailList= new ArrayList<>();

        File productDetailFile1=new File("D:/worktest/schooloto/image/1.jpg");
        InputStream ins1=new FileInputStream(productDetailFile1);
        ImageHolder imageHolder1=new ImageHolder(ins1,productDetailFile1.getName());

        File productDetailFile2=new File("D:/worktest/schooloto/image/2.jpg");
        InputStream ins2=new FileInputStream(productDetailFile2);
        ImageHolder imageHolder2=new ImageHolder(ins2,productDetailFile2.getName());

        prodImgDetailList.add(imageHolder1);
        prodImgDetailList.add(imageHolder2);

        // 调用服务
        ProductExecution pe=productService.addProduct(product,imageHolder,prodImgDetailList);
        System.out.println(pe.getState());
    }

    @Test
    public void testModifyProduct() throws Exception{

        // 注意表中的外键关系，确保这些数据在对应的表中的存在
        ProductCategory productCategory=new ProductCategory();

        productCategory.setProductCategoryId(2l);

        Shop shop=new Shop();
        shop.setShopId(3l);

        // 构造Product
        Product product=new Product();
        product.setProductName("test_product1-modify");
        product.setProductDesc("product1 desc-modify");

        product.setNormalPrice("100");
        product.setPromotionPrice("8");
        product.setPriority(66);
        product.setLastEditTime(new Date());
        product.setProductCategory(productCategory);
        product.setShop(shop);

        product.setProductId(5l);
        // 构造 商品图片
        File productFile=new File("D:/worktest/schooloto/image/20190618151801.jpg");
        InputStream ins=new FileInputStream(productFile);
        ImageHolder imageHolder=new ImageHolder(ins,productFile.getName());

        // 构造商品详情图片
        List<ImageHolder> prodImgDetailList= new ArrayList<>();

        File productDetailFile1=new File("D:/worktest/schooloto/image/1.jpg");
        InputStream ins1=new FileInputStream(productDetailFile1);
        ImageHolder imageHolder1=new ImageHolder(ins1,productDetailFile1.getName());

        File productDetailFile2=new File("D:/worktest/schooloto/image/2.jpg");
        InputStream ins2=new FileInputStream(productDetailFile2);
        ImageHolder imageHolder2=new ImageHolder(ins2,productDetailFile2.getName());

        prodImgDetailList.add(imageHolder1);
        prodImgDetailList.add(imageHolder2);

        // 调用服务
        ProductExecution pe=productService.modifyProduct(product,imageHolder,prodImgDetailList);
        System.out.println(pe.getState());
    }
}
