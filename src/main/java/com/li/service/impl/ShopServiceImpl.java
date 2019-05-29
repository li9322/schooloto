package com.li.service.impl;

import com.li.dao.ShopDao;
import com.li.dto.ShopExecution;
import com.li.entity.Shop;
import com.li.enums.ShopStateEnum;
import com.li.exception.ShopOperationException;
import com.li.service.ShopService;
import com.li.util.FileUtil;
import com.li.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * @ClassName: ShopServiceImpl
 * @Description:
 * @author: libl
 * @date: 2019/05/29 15:18
 */
@Service
public class ShopServiceImpl implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private ShopDao shopDao;

    /**
     * 3个步骤需要在一个事务中, 添加@Transactional注解
     * 1. 将shop基本信息添加到数据库，返回shopId
     * 2. 根据shopId创建目录,得到图片存储的相对路径
     * 3. 将相对路径更新到数据库
     * Spring默认情况下会对运行期例外(RunTimeException)进行事务回滚
     * （1）注解@Transactional 只能应用到 public 方法才有效
     * （2）在 Spring 的 AOP 代理下，只有目标方法由外部调用，目标方法才由 Spring成的代理对象来管理，这会造成自调用问题。
     * 若同一类中的其他没有@Transactional 注解的方法内部调用有@Transactional 注解的方法，
     * 有@Transactional注解的方法的事务被忽略，不会发生回滚。
     *
     * 上面的两个问题@Transactional 注解只应用到 public 方法和自调用问题，是由于使用 Spring AOP
     * 代理造成的。为解决这两个问题，可以使用 AspectJ 取代 Spring AOP 代理
     *
     * 在应用系统调用声明@Transactional 的目标方法时，Spring Framework 默认使用 AOP代理，在代码运行时生成一个代理对象，
     * 根据@Transactional 的属性配置信息，这个代理对象决定该声明@Transactional的目标方法是否由拦截器 TransactionInterceptor 来使用拦截，
     * 在 TransactionInterceptor拦截时，会在在目标方法开始执行之前创建并加入事务，并执行目标方法的逻辑,最后根据执行情况是否出现异常，
     * 利用抽象事务管理器AbstractPlatformTransactionManager 操作数据源DataSource 提交或回滚事务
     */
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        // 非空判断 (这里先判断shop是否为空，严格意义上讲shop中的are的属性也需要判断)
        if (shop == null)
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        // 关键步骤1. 设置基本信息，插入shop
        // 初始状态： 审核中
        shop.setEnableStatus(0);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());

        int effectedNum = shopDao.insertShop(shop);
        if (effectedNum <= 0)
            throw new ShopOperationException("店铺创建失败");
        else
            // 关键步骤2. 添加成功,则继续处理文件,获取shopid,用于创建图片存放的目录
            if (shopImg != null) {
                try {
                    // 需要根据shopId来创建目录,所以也需要shop这个入参
                    addShopImg(shop, shopImg);
                } catch (Exception e) {
                    logger.error("addShopImg error{}", e.toString());
                    throw new ShopOperationException("addShopImg error:" + e.getMessage());
                }
                // 关键步骤3. 更新tb_shop中 shop_img字段
                effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    logger.error("updateShop error{}", "更新店铺失败");
                    throw new ShopOperationException("updateShop error");
                }
            }
        // 返回店铺的状态：审核中，以及店铺信息
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    /**
     * @Description:根据shopId创建目录,并生成水印图片
     * @Param:shop,shopImg
     * @return:void
     * @Author: li
     */
    private void addShopImg(Shop shop, File shopImg) {
        String imgPath = FileUtil.getShopImagePath(shop.getShopId());
        // 生成图片的水印图
        String relativeAddr = ImageUtil.generateThumbnails(shopImg, imgPath);
        // 将相对路径设置个shop,用于更新数据库
        shop.setShopImg(relativeAddr);
    }
}
