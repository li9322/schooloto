package com.li.dao;

import com.li.entity.Product;

/**
 * @ClassName: ProductDao
 * @Description:
 * @author: libl
 * @date: 2019/06/06 15:46
 */
public interface ProductDao {
    /**
     * @Description: 增加商品
     * @Param: product
     * @return: int
     */
    int insertProduct(Product product);
}
