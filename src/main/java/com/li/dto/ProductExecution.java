package com.li.dto;


import com.li.entity.Product;
import com.li.enums.ProductStateEnum;

import java.util.List;

/**
 * @ClassName: ProductCategoryExecution
 * @Description: 操作Product返回的DTO
 * @author: libl
 * @date: 2019/06/10 15:17
 */
public class ProductExecution {
    /**
     * 操作结果状态
     */
    private int state;

    /**
     * 操作结果状态说明
     */
    private String stateInfo;

    /**
     * 操作成功的总量
     */
    private int count;


    /**
     * 批量操作（查询商品列表）返回的Product集合
     */
    private List<Product> productList;

    /**
     * 增删改的操作返回的商品信息
     */
    private Product product;

    /**
     * @Title:ProductExecution
     * @Description:默认构造函数
     */
    public ProductExecution() {
    }

    /**
     * @Title: ProductExecution
     * @Description: 单个操作成功时返回的ProductExecution
     * @param stateEnum
     * @param product
     */
    public ProductExecution(ProductStateEnum stateEnum, Product product) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

    /**
     * @Title: ProductExecution
     * @Description: 批量操作成功的时候返回的ProductExecution
     * @param stateEnum
     * @param productList
     * @param count
     */
    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList, int count) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
        this.count = count;
    }

    /**
     * @Description: 操作失败的时候返回的ProductExecution，仅返回状态信息即可
     * @Param: productStateEnum
     */
    public ProductExecution(ProductStateEnum productStateEnum){
        this.state=productStateEnum.getState();
        this.stateInfo=productStateEnum.getStateInfo();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
