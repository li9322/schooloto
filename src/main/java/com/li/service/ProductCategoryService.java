package com.li.service;


import com.li.dto.ProductCategoryExecution;
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
}
