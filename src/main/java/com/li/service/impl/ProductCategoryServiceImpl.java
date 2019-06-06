package com.li.service.impl;

import com.li.dao.ProductCategoryDao;
import com.li.dto.ProductCategoryExecution;
import com.li.entity.ProductCategory;
import com.li.enums.ProductCategoryStateEnum;
import com.li.exception.ProductCategoryOperationException;
import com.li.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: ProductCategoryServiceImpl
 * @Description: 使用@Service，交由Spring托管
 * @author: libl
 * @date: 2019/06/05 10:03
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> queryProductCategoryList(long shopId) {
        return productCategoryDao.selectProductCategoryList(shopId);
    }

    /**
     * 使用@Transactional控制事务
     */
    @Override
    @Transactional
    public ProductCategoryExecution addProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        // 非空判断
        if (productCategoryList!=null && productCategoryList.size()>0){
            try {
                // 批量增加ProductCategory
                int effectNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectNum>0)
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS,productCategoryList,effectNum);
                else
                    return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
            }catch (Exception e){
                e.printStackTrace();
                throw new ProductCategoryOperationException("batchAddProductCategory Error:" +e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPETY_LIST);
        }
    }

    /**
     * TODO 需要先将该商品目录下的商品的类别Id置为空，然后再删除该商品目录， 因此需要事务控制@Transactional
     */
    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {

        try {
            int effectNum=productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if (effectNum>0)
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            else
                return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
        }catch (Exception e){
            throw new ProductCategoryOperationException(e.getMessage());
        }
    }
}
