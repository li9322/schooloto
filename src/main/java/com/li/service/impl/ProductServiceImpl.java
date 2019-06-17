package com.li.service.impl;

import com.li.dao.ProductDao;
import com.li.dao.ProductImgDao;
import com.li.dto.ImageHolder;
import com.li.dto.ProductExecution;
import com.li.entity.Product;
import com.li.entity.ProductImg;
import com.li.exception.ProductOperationException;
import com.li.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName: ProductServiceImpl
 * @Description: @Service 标识的服务层
 * @author: libl
 * @date: 2019/06/10 16:45
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    ProductImgDao productImgDao;

    @Deprecated
    @Override
    public ProductExecution addProduct(Product product, InputStream prodImgIns, String prodImgName, List<InputStream> prodImgDetailInsList, List<String> prodImgDetailNameList) throws ProductOperationException {
        return null;
    }

    /**
     * 注意事务控制@Transactional
     * 步骤如下：
     * 1.处理商品的缩略图，获取相对路径，为了调用dao层的时候写入 tb_product中的 img_addr字段有值
     * 2.写入tb_product ，获取product_id
     * 3.集合product_id 批量处理商品详情图片
     * 4.将商品详情图片 批量更新到 tb_proudct_img表
     */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> prodImgDetailList) throws ProductOperationException {
        return null;
    }
}
