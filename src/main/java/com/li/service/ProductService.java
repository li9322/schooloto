package com.li.service;

import com.li.dto.ImageHolder;
import com.li.dto.ProductExecution;
import com.li.entity.Product;
import com.li.exception.ProductOperationException;

import java.awt.font.ImageGraphicAttribute;
import java.io.InputStream;
import java.util.List;


/**
 * @ClassName: ProductService
 * @Description:
 * @author: libl
 * @date: 2019/06/10 15:44
 */
public interface ProductService {
    /**
     * @param product               商品信息
     * @param prodImgIns            商品缩略图输入流
     * @param prodImgName           商品缩略图名称
     * @param prodImgDetailInsList  商品详情图片的输入流
     * @param prodImgDetailNameList 商品详情图片的名称
     * @throws ProductOperationException
     * @Title: addProduct  废弃的方法
     * @Description: 新增商品 。 因为无法从InputStream中获取文件的名称，所以需要指定文件名
     * 需要传入的参数太多，我们将InputStream 和 ImgName封装到一个实体类中，便于调用。
     * 及早进行优化整合，避免后续改造成本太大
     * @return: ProductExecution
     */
    @Deprecated
    ProductExecution addProduct(Product product, InputStream prodImgIns, String prodImgName,
                                List<InputStream> prodImgDetailInsList, List<String> prodImgDetailNameList) throws ProductOperationException;

    /**
     * @param product           产品信息
     * @param imageHolder       产品缩略图的封装信息
     * @param prodImgDetailList 产品详情图片的封装信息
     * @return
     * @throws ProductOperationException
     * @Title: addProduct
     * @Description: 重构后的addProduct
     * @return: ProductExecution
     */
    ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> prodImgDetailList) throws ProductOperationException;

    /**
     * @Description: 根据productId查询product
     * @Param: productId
     * @return: Product
     */
    Product queryProductById(long productId);

    /**
     * @Description: 修改商品
     * @Param: product  产品信息
     * @Param: imageHolder  产品缩略图的封装信息
     * @Param: prodImgDetailList 产品详情图片的封装信息
     * @return: ProductExecution
     */
    ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> prodImgDetailList) throws ProductOperationException;

    /**
     * @Description: 查询
     * @Param: productCondition
     * @Param: pageIndex 前端页面 只有第几页 第几页 定义为pageIndex
     * @Param: pageSize 一页中展示的行数
     * @return: ProductExecution
     */
    ProductExecution queryProductionList(Product productCondition, int pageIndex, int pageSize) throws ProductOperationException;
}
