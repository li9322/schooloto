package com.li.entity;


import java.util.Date;

public class ShopCategory {
    /**
     * 主键
     */
    private Long shopCategoryId;
    private String shopCategoryName;
    /**
     * 店铺目录描述
     */
    private String shopCategoryDesc;
    /**
     * 店铺目录对应的图片地址
     */
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date LastEditTime;
    /**
     * 上级Id,用于店铺目录的分层展示
     */
    private Long parentId;

    public Long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return LastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        LastEditTime = lastEditTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ShopCategory{" +
                "shopCategoryId=" + shopCategoryId +
                ", shopCategoryName='" + shopCategoryName + '\'' +
                ", shopCategoryDesc='" + shopCategoryDesc + '\'' +
                ", shopCategoryImg='" + shopCategoryImg + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", LastEditTime=" + LastEditTime +
                ", parentId=" + parentId +
                '}';
    }
}
