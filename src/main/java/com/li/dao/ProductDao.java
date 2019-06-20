package com.li.dao;

import com.li.entity.Product;
import com.li.entity.ProductImg;

import java.util.List;

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

    /**
     * @Description: 根据productId查询product
     * @Param: productId
     * @return: Product
     */
    Product selectProductById(long productId);

    /**
     * @Description: 修改商品
     * @Param: product
     * @return: int
     */
    int updateProduct(Product product);

}
