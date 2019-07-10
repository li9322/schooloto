package com.li.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: FrontEndController
 * @Description:
 * @author: libl
 * @date: 2019/07/01 10:29
 */
@Controller
@RequestMapping("/frontend")
public class FrontEndController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "frontend/index";
    }

    @RequestMapping(value = "/shoplist",method = RequestMethod.GET)
    public String shopList(){
        return "frontend/shoplist";
    }

    @RequestMapping(value = "/shopdetail",method = RequestMethod.GET)
    public String shopDetail(){
        return "frontend/shopdetail";
    }
}
