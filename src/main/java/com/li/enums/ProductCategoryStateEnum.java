package com.li.enums;

/**
 * @ClassName: ProductCategoryStateEnum
 * @Description: 将对ProductCategoryState的状态信息封装到ProductCategoryStateEnum中
 * @author: libl
 * @date: 2019/06/05 10:53
 */
public enum  ProductCategoryStateEnum {
    SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"操作失败"),NULL_SHOP(-1002,"Shop信息为空"),EMPETY_LIST(-1003,"请输入商品目录信息");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * @Description: 构造函数
     * @Param: state
     * @Param: stateInfo
     */
    private ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * @Description: 通过state获取productCategoryStateEnum, 从而可以调用ProductCategoryStateEnum
     *               #getStateInfo()获取stateInfo
     * @Param: index
     * @return: ProductCategoryStateEnum
     */
    public static ProductCategoryStateEnum stateOf(int index){
        for (ProductCategoryStateEnum productCategoryStateEnum:values()){
            if (productCategoryStateEnum.getState()==index)
                return productCategoryStateEnum;
        }
        return null;
    }
}
