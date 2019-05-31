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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        shop.setShopName("咖啡店Improve");
        shop.setShopDesc("li的咖啡店Improve");
        shop.setShopAddr("beijing-Improve");
        shop.setPhone("2643");
        shop.setPriority(99);
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中Improve");

        File shopFile = new File("D:/worktest/schooloto/image/20190529142901.jpg");

        ShopExecution se = null;
        InputStream ins = null;
        try {
            ins = new FileInputStream(shopFile);
            se = shopService.addShop(shop, ins, shopFile.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(ShopStateEnum.CHECK.getState() + "-----" + se.getState());
    }
    @Test
    public void testModifyShop(){
        Shop shop = new Shop();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        shop.setShopId(3L);

        area.setAreaId(2);
        shopCategory.setShopCategoryId(2L);

        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("Modify咖啡店");
        shop.setShopDesc("Modifyli的咖啡店");
        shop.setShopAddr("Modify-beijing");
        shop.setPhone("12643");
        shop.setPriority(88);

        File shopFile = new File("D:/worktest/schooloto/image/20190529142902.jpg");

        ShopExecution se = null;
        InputStream ins = null;
        try {
            ins = new FileInputStream(shopFile);
            se = shopService.modifyShop(shop, ins, shopFile.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(ShopStateEnum.CHECK.getState() + "-----" + se.getState());
    }
}
