package com.li.enums;

import com.li.entity.Shop;

/**
 * @ClassName: ShopStateEnum
 * @Description:使用枚举表述常量数据字典
 * @author: libl
 * @date: 2019/05/29 14:41
 */
public enum ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"), PASS(2, "审核通过"), INNER_ERROR(-1001, "操作失败"), NULL_SHOPID(-1002, "ShopId为空"), NULL_SHOP_INFO(-1003, "传入了空的信息");

    private int state;
    private String stateInfo;

    /**
     * @Description: 私有构造函数, 禁止外部初始化改变定义的常量
     * @Param: state stateInfo
     * @Author: li
     */
    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * @Description: 仅设置get方法, 禁用set
     */
    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * @Description: 定义换成pulic static 暴漏给外部,通过state获取ShopStateEnum
     *               values()获取全部的enum常量
     * @Param: state
     * @return: ShopStateEnum
     * @Author: li
     */
    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}
