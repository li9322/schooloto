package com.li.enums;

/**
 * @ClassName: ProductStateEnum
 * @Description: 使用枚举表述常量数据字典
 * @author: libl
 * @date: 2019/06/10 15:22
 */
public enum ProductStateEnum {
    SUCCESS(1, "操作成功"), INNER_ERROR(-1001, "操作失败"), NULL_PARAMETER(-1002, "缺少参数");

    private int state;
    private String stateInfo;

    /**
     * @param state
     * @param stateInfo
     * @Title:ProductStateEnum
     * @Description:私有构造函数,禁止外部初始化改变定义的常量
     */
    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * @return
     * @Title: getState
     * @Description: 仅设置get方法, 禁用set
     * @return: int
     */
    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * @param state
     * @Title: stateOf
     * @Description: 定义换成pulic static 暴漏给外部,通过state获取ShopStateEnum
     * <p>
     * values()获取全部的enum常量
     * @return: ShopStateEnum
     */
    public static ProductStateEnum stateOf(int state) {
        for (ProductStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state)
                return stateEnum;
        }
        return null;
    }
}
