package com.li.service;


import com.li.dto.ProductCategoryExecution;
import com.li.entity.Product;
import com.li.entity.ProductCategory;
import com.li.exception.ProductCategoryOperationException;

import java.util.List;

/**
 * @ClassName: ProductCategoryService
 * @Description:
 * @author: libl
 * @date: 2019/06/05 10:01
 */
public interface ProductCategoryService {
    List<ProductCategory> queryProductCategoryList(long shopId);
    ProductCategoryExecution addProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * @Description: TODO 需要先将该商品目录下的商品的类别Id置为空，然后再删除该商品目录， 因此需要事务控制
     * @Param: productCategoryId
     * @Param: shopId
     * @return: ProductCategoryExecution
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId) throws ProductCategoryOperationException;
}
