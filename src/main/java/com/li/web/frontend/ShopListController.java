package com.li.web.frontend;

import com.li.dto.ShopExecution;
import com.li.entity.Area;
import com.li.entity.Shop;
import com.li.entity.ShopCategory;
import com.li.service.AreaService;
import com.li.service.ShopCategoryService;
import com.li.service.ShopService;
import com.li.util.HTTPServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopListController
 * @Description:
 * @author: libl
 * @date: 2019/07/01 16:04
 */
@Controller
@RequestMapping("/frontend")
public class ShopListController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "/listshopspageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        long parentId = HTTPServletRequestUtil.getLong(request, "parentId");
        List<ShopCategory> shopCategoryList = null;

        if (parentId != -1) {
            try {
                ShopCategory childCategory = new ShopCategory();
                ShopCategory parentCategory = new ShopCategory();
                parentCategory.setShopCategoryId(parentId);
                childCategory.setParent(parentCategory);
                shopCategoryList = shopCategoryService.getShopCategoryList(childCategory);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {

            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaService.getAreaList();
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
            return modelMap;
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    @RequestMapping(value = "/listshops", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShops(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int pageIndex=HTTPServletRequestUtil.getInt(request,"pageIndex");
        int pageSize=HTTPServletRequestUtil.getInt(request,"pageSize");
        if ((pageIndex>-1)&&(pageSize>-1)){
            long parentId=HTTPServletRequestUtil.getLong(request,"parentId");
            long shopCategoryId=HTTPServletRequestUtil.getLong(request,"shopCategoryId");
            int areaId=HTTPServletRequestUtil.getInt(request,"areaId");
            String shopName= HTTPServletRequestUtil.getString(request,"shopName");

            Shop shopCondition=compactShopCondition4Search(parentId,shopCategoryId,areaId,shopName);

            ShopExecution se=shopService.getShopList(shopCondition,pageIndex,pageSize);
            modelMap.put("shopList",se.getShopList());
            modelMap.put("count",se.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex");
        }
        return modelMap;
    }

    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
        Shop shopCondition=new Shop();
        if (parentId!=-1l){
            ShopCategory childCategory=new ShopCategory();
            ShopCategory parentCategory=new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            childCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childCategory);
        }
        if (shopCategoryId!=-1l){
            ShopCategory shopCategory=new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId!=-1){
            Area area=new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }
        if (shopName!=null){
            shopCondition.setShopName(shopName);
        }

        shopCondition.setEnableStatus(1);
        return shopCondition;
    }
}
