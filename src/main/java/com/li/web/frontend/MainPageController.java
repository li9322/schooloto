package com.li.web.frontend;

import com.li.entity.HeadLine;
import com.li.entity.ShopCategory;
import com.li.enums.HeadLineStateEnum;
import com.li.enums.ShopCategoryStateEnum;
import com.li.service.HeadLineService;
import com.li.service.ShopCategoryService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MainPageController
 * @Description:
 * @author: libl
 * @date: 2019/07/01 10:01
 */
@Controller
@RequestMapping("/frontend")
public class MainPageController {

    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "/listmainpage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> lsitMainPage() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<HeadLine> headLineList = new ArrayList<>();
        try {
            // 查询状态为1的可见的headLine信息
            HeadLine headLineCondition = new HeadLine();
            headLineCondition.setEnableStatus(1);
            headLineList = headLineService.queryHeadLineList(headLineCondition);

            modelMap.put("headLineList", headLineList);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("errMsg", HeadLineStateEnum.INNER_ERROR.getStateInfo());
        }

        try {
            // 查询parentId为null的一级类别
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList", shopCategoryList);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", ShopCategoryStateEnum.INNER_ERROR.getStateInfo());
        }

        modelMap.put("success", true);

        return modelMap;
    }
}
