package com.li.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: ShopAdminController
 * @Description:
 * @author: libl
 * @date: 2019/05/30 13:45
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation",method = RequestMethod.GET)
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist",method = RequestMethod.GET)
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement",method = RequestMethod.GET)
    public String shopManagement(){
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanage",method = RequestMethod.GET)
    public String productCategoryManage(){
        return "shop/productcategorymanage";
    }

    @RequestMapping(value = "/productoperation",method = RequestMethod.GET)
    public String productManage1(){
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productmanagement",method = RequestMethod.GET)
    public String productManage(){
        return "shop/productmanagement";
    }

    @RequestMapping(value = "/localauthlogin",method = RequestMethod.GET)
    public String localAuthlogin(){
        return "shop/localauthlogin";
    }
}
