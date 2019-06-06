package com.li.dao;

import com.li.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ProductCategoryDao
 * @Description:
 * @author: libl
 * @date: 2019/06/05 9:17
 */
public interface ProductCategoryDao {
    List<ProductCategory> selectProductCategoryList(long shopId);

    /**
     * @Description: 批量增加roductCategory
     * @Param: productCategoryList
     * @return: int
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * @Description:删除特定shop下的productCategory
     * @Param:productCategoryId
     * @Param:shopId
     * @return:int
     */
    int deleteProductCategory(@Param("productCategoryId")Long productCategoryId,@Param("shopId") Long shopId);
}
