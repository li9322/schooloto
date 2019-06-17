package com.li.dao;

import com.li.BaseTest;
import com.li.entity.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProductDaoTest
 * @Description:
 * @author: libl
 * @date: 2019/06/06 16:46
 */
public class ProductImgDaoTest extends BaseTest {

    @Autowired
    ProductImgDao productImgDao;

    @Test
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
}
