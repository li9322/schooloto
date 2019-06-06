package com.li.dao;

import com.li.BaseTest;
import com.li.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProductCategoryTest
 * @Description:Junit 4.11里增加了指定测试方法执行顺序的特性 .
 * <p>
 * 测试类的执行顺序可通过对测试类添加注解@FixMethodOrder(value) 来指定,其中value 为执行顺序
 * <p>
 * 三种执行顺序可供选择：
 * <p>
 * 默认（MethodSorters.DEFAULT）,
 * 默认顺序由方法名hashcode值来决定，如果hash值大小一致，则按名字的字典顺序确定
 * 由于hashcode的生成和操作系统相关
 * (以native修饰），所以对于不同操作系统，可能会出现不一样的执行顺序，在某一操作系统上，多次执行的顺序不变
 * <p>
 * 按方法名（ MethodSorters.NAME_ASCENDING）【推荐】,
 * 按方法名称的进行排序，由于是按字符的字典顺序，所以以这种方式指定执行顺序会始终保持一致；
 * 不过这种方式需要对测试方法有一定的命名规则，如 测试方法均以testNNN开头（NNN表示测试方法序列号 001-999）
 * <p>
 * JVM（MethodSorters.JVM）
 * 按JVM返回的方法名的顺序执行，此种方式下测试方法的执行顺序是不可预测的，即每次运行的顺序可能都不一样
 * @author: libl
 * @date: 2019/06/06 10:45
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryTest extends BaseTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void testB_SelectProductCategoryList() {
        Long shopId = 2L;
        List<ProductCategory> productCategories = productCategoryDao.selectProductCategoryList(shopId);
        System.out.println(productCategories.size());
        for (ProductCategory productCategory : productCategories)
            System.out.println(productCategory);

        productCategories = productCategoryDao.selectProductCategoryList(6l);
        System.out.println(productCategories.size());
        System.out.println("-----------------------");
    }

    @Test
    public void testA_BatchInsertProductCategory() {
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

    @Test
    public void testC_DeleteProductCategory(){
        List<ProductCategory> productCategoryList=productCategoryDao.selectProductCategoryList(2L);
        for (ProductCategory productCategory:productCategoryList)
            if ("product1".equals(productCategory.getProductCategoryName())||"product2".equals(productCategory.getProductCategoryName())){
                int effectNum=productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(),2l);
                System.out.println(effectNum);
            }
    }
}
