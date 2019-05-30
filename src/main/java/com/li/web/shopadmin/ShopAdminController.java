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
}
