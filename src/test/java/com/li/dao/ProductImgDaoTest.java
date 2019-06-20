package com.li.dao;

import com.li.BaseTest;
import com.li.entity.ProductImg;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProductDaoTest
 * @Description: 测试类的执行顺序可通过对测试类添加注解@FixMethodOrder(value) 来指定
 * @author: libl
 * @date: 2019/06/06 16:46
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {

    @Autowired
    ProductImgDao productImgDao;

    @Test
    @Ignore
    public void testInsertProduct() {

        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("/aaa1/bbb1");
        productImg1.setImgDesc("商品详情图片1");
        productImg1.setPriority(32);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(2l);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("/aaa2/bbb2");
        productImg2.setImgDesc("商品详情图片2");
        productImg2.setPriority(33);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(2l);

        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);

        int effectNum = productImgDao.batchInsertProductImg(productImgList);
        System.out.println(effectNum);

    }

    @Test
    public void testA_BatchInsertProductImg() {

        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("/aaa5/bbb5");
        productImg1.setImgDesc("商品详情图片5");
        productImg1.setPriority(35);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(3l);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("/aaa6/bbb6");
        productImg2.setImgDesc("商品详情图片6");
        productImg2.setPriority(36);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(3l);

        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);

        int effectNum = productImgDao.batchInsertProductImg(productImgList);
        System.out.println(effectNum);

    }

    @Test
    public void testB_DeleteProductImgById() {
        long productId = 3l;
        int effectNum = productImgDao.deleteProductImgById(productId);
        System.out.println(effectNum);
    }
}
