package com.li.dao;

import com.li.entity.ProductImg;

import java.util.List;

/**
 * @ClassName: ProductImgDao
 * @Description:
 * @author: libl
 * @date: 2019/06/06 16:05
 */
public interface ProductImgDao {
    /**
     * @Description: 一个商品下可能拥有多个图片，所以这里是批量新增商品图片
     * @Param: productImgList
     * @return: int
     */
    int batchInsertProductImg(List<ProductImg> productImgList);


    /**
     * @Description: 删除商品对应的商品详情图片
     * @Param: productId
     * @return: int
     */
    int deleteProductImgById(long productId);

    /**
     * @Description: 根据productId查询商铺对应的图片详情信息
     * @Param: productId
     * @return: List<ProductImg>
     */
    List<ProductImg> selectProductImgList(long productId);

}
