package com.li.web.frontend;

import com.li.dto.ProductExecution;
import com.li.entity.Product;
import com.li.entity.ProductCategory;
import com.li.entity.Shop;
import com.li.service.ProductCategoryService;
import com.li.service.ProductService;
import com.li.service.ShopService;
import com.li.util.HTTPServletRequestUtil;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
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
 * @ClassName: ShopDetailController
 * @Description:
 * @author: libl
 * @date: 2019/07/05 11:07
 */
@Controller
@RequestMapping("/frontend")
public class ShopDetailController {
    @Autowired
    ShopService shopService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> lsitShopDeatilPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        long shopId = HTTPServletRequestUtil.getLong(request, "shopId");
        Shop shop = null;
        List<ProductCategory> productCategoryList = null;
        if (shopId != -1) {
            shop = shopService.getShopById(shopId);
            productCategoryList = productCategoryService.queryProductCategoryList(shopId);
            modelMap.put("shop", shop);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listProductsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int pageIndex = HTTPServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HTTPServletRequestUtil.getInt(request, "pageSize");
        long shopId = HTTPServletRequestUtil.getLong(request, "shopId");
        if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
            long productCategoryId = HTTPServletRequestUtil.getLong(request, "productCategoryId");
            String productName = HTTPServletRequestUtil.getString(request, "productName");
            Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
            ProductExecution pe = productService.queryProductionList(productCondition, pageIndex, pageSize);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        if (productCategoryId != -1) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if (productName != null)
            productCondition.setProductName(productName);
        productCondition.setEnableStatus(1);
        return productCondition;
    }
}
