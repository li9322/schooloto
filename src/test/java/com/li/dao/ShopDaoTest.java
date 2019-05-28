package com.li.dao;

import com.li.BaseTest;
import com.li.entity.Area;
import com.li.entity.PersonInfo;
import com.li.entity.Shop;
import com.li.entity.ShopCategory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopDaoTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ShopDaoTest.class);

    @Autowired
    ShopDao shopDao;

    @Test
    public void testQueryArea() {
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        // 因为tb_shop表中有外键约束,因此务必确保 设置的这几个id在对应的表中存在.
        // 我们提前在tb_person_inf tb_area
        // tb_shop_category分别添加了如下id的数据,以避免插入tb_shop时抛出如下异常
        // com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException:
        // Cannot add or update a child row: a foreign key constraint fails
        // (`o2o`.`tb_shop`, CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`)
        // REFERENCES `tb_area` (`area_id`))

        personInfo.setUserId(1L);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(personInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("Artisan");
        shop.setShopDesc("ArtisanDesc");
        shop.setShopAddr("beijing");
        shop.setPhone("123456");
        shop.setShopImg("/xxx/xxx");
        shop.setPriority(99);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("Waring");

        int effectNum = shopDao.insertShop(shop);
        System.out.println(effectNum);

    }
}
