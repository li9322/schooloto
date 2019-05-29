package com.li.service;

import com.li.BaseTest;
import com.li.dto.ShopExecution;
import com.li.entity.Area;
import com.li.entity.PersonInfo;
import com.li.entity.Shop;
import com.li.entity.ShopCategory;
import com.li.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

/**
 * @ClassName: ShopServiceTest
 * @Description:
 * @author: libl
 * @date: 2019/05/29 16:26
 */
public class ShopServiceTest extends BaseTest {
    @Autowired
    ShopService shopService;

    @Test
    public void testAddShop() {
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        personInfo.setUserId(1L);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(personInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("咖啡店");
        shop.setShopDesc("li的咖啡店");
        shop.setShopAddr("beijing");
        shop.setPhone("2643");
        shop.setPriority(99);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");

        File shopFile = new File("D:/worktest/schooloto/image/20190529142901.jpg");

        ShopExecution se = shopService.addShop(shop, shopFile);
        System.out.println(ShopStateEnum.CHECK.getState() + "-----" + se.getState());
    }
}
