package com.li.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.dto.ShopExecution;
import com.li.entity.Area;
import com.li.entity.PersonInfo;
import com.li.entity.Shop;
import com.li.entity.ShopCategory;
import com.li.enums.ShopStateEnum;
import com.li.exception.ShopOperationException;
import com.li.service.AreaService;
import com.li.service.ShopCategoryService;
import com.li.service.ShopService;
import com.li.util.HTTPServletRequestUtil;
import com.li.util.VerifiyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopController
 * @Description:
 * @author: libl
 * @date: 2019/05/30 9:07
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    /**
     * @Description:
     * @Param: 因为是接收前端的请求，而前端的信息都封装在HttpServletRequest中，所以需要解析HttpServletRequest，获取必要的参数
     * <p>
     * 1. 接收并转换相应的参数，包括shop信息和图片信息 2. 注册店铺 3. 返回结果给前台
     * @return:Map<String, Object>
     * @Author: li
     */
    @RequestMapping(value = "/registshop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 0. 验证码校验
        if (!VerifiyCodeUtil.verifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码不正确");
            return modelMap;
        }

        // 1. 接收并转换相应的参数，包括shop信息和图片信息

        // 1.1 shop信息

        // shopStr 是和前端约定好的参数值，后端从request中获取request这个值来获取shop的信息
        String shopStr = HTTPServletRequestUtil.getString(request, "shopStr");
        // 使用jackson-databind 将json转换为pojo
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            // 将json转换为pojo
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            e.printStackTrace();
            // 将错误信息返回给前台
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }

        // 1.2 图片信息 基于Apache Commons FileUpload的文件上传

        // Spring MVC中的 图片存在CommonsMultipartFile中
        CommonsMultipartFile shopImg = null;
        // 从request的本次会话中的上下文中获取图片的相关内容
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // shopImg是和前端约定好的变量名
            shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
        } else {
            // 将错误信息返回给前台
            modelMap.put("success", false);
            modelMap.put("errMsg", "图片不能为空");
            return modelMap;
        }

        // 2. 注册店铺
        if (shop != null && shopImg != null) {
            // Session TODO
            // 店主persionInfo的信息，肯定要登录才能注册店铺。
            // 所以这部分信息我们从session中获取，尽量不依赖前端,这里暂时时不具备条件，后续改造，先硬编码，方便单元测试
            //PersonInfo personInfo = new PersonInfo();
            //personInfo.setUserId(1L);

            // 注册店铺之前登录，登录成功后，约定好将user这个key 设置到session中，
            // 这里通过key就可以取到PersonInfo的信息
            PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(personInfo);

            shop.setOwner(personInfo);
            // 注册店铺

            // se = shopService.addShop(shop, shopImg); 改造前的调用方式
            // 这个时候，我们从前端获取到的shopImg是CommonsMultipartFile类型的，如何将CommonsMultipartFile转换为file.
            // 网上也有将CommonsMultipartFile转换为File的方法，并通过maxInMemorySize的设置尽量不产生临时文件
            // 这里我们换个思路,因为CommonsMultipartFile可以获取InputStream,Thumbnailator又可以直接处理输入流
            // 因为InputStream中我们无法得到文件的名称，而thumbnail中需要根据文件名来获取扩展名，所以还要再加一个参数String类型的fileName
            // 既然第二个和第三个参数都是通过shopImg获取的，为什么不直接传入一个shopImg呢？
            // 主要是为了service层单元测测试的方便，因为service层很难实例化出一个CommonsMultipartFile类型的实例
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    modelMap.put("errrMsg", "注册成功");
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errmMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                e.printStackTrace();
                modelMap.put("success", false);
                modelMap.put("errMsg", "addShop Error");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
        }
        return modelMap;
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: li
     */
    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = null;
        List<Area> areaList = null;
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("success", true);
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
     * @Description:根据shopId获取shop信息，接收前端的请求，获取shopId ，所以入参为HttpServletRequest
     * @ResponseBody不需要VIEW展现层模块，直接显示到客户端的内容。 将内容或对象作为 HTTP 响应正文返回
     * @return: Map<String, Object>
     * @Author: li
     */
    @RequestMapping(value = "/getshopinfobyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopInfoById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // shopId 为和前端约定好的变量
        int shopId = HTTPServletRequestUtil.getInt(request, "shopId");
        try {
            if (shopId >= 0) {
                // 查询 ，按照设计，我们希望前端页面下拉列表中可以修改区域信息，所以需要查询出来全量的区域列表
                // 对已ShopCategory而言，我们DAO层的SQL已经将shop_category_id和
                // shop_category_name 查询出来，前端设置到对应的属性上即可。没有必要全部查询出来。
                Shop shop = shopService.getShopById(shopId);
                List<Area> areaList = areaService.getAreaList();

                modelMap.put("success", true);
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
            } else {
                modelMap.put("success", false);
                modelMap.put("errrMsg", "shopId不合法");
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    /**
     * @Description:
     * @Param: 因为是接收前端的请求，而前端的信息都封装在HttpServletRequest中，所以需要解析HttpServletRequest，获取必要的参数
     * 1. 接收并转换相应的参数，包括shop信息和图片信息 2.修改店铺 3. 返回结果给前台
     * @return: Map<String, Object>
     * @Author: li
     */
    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        // 0. 验证码校验
        if (!VerifiyCodeUtil.verifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码不正确");
            return modelMap;
        }
        // 1. 接收并转换相应的参数，包括shop信息和图片信息
        // 1.1 shop信息
        // shopStr 是和前端约定好的参数值，后端从request中获取request这个值来获取shop的信息
        String shopStr = HTTPServletRequestUtil.getString(request, "shopStr");
        // 使用jackson-databind 将json转换为pojo
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            // 将json转换为pojo
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            e.printStackTrace();
            // 将错误信息返回给前台
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        // 1.2 图片信息 基于Apache Commons FileUpload的文件上传 （ 修改商铺信息 图片可以不更新）

        // Spring MVC中的 图片存在CommonsMultipartFile中
        CommonsMultipartFile shopImg = null;
        // 从request的本次会话中的上线文中获取图片的相关内容
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // shopImg是和前端约定好的变量名
            shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
        }

        // 2. 修改店铺
        if (shop != null && shop.getShopId() != null) {
            // Session 部分的 PersonInfo 修改商铺是不需要的设置的。
            // 修改店铺
            ShopExecution se = null;
            try {
                if (shopImg != null)
                    se = shopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                else
                    se = shopService.modifyShop(shop, null, null);
                // 成功
                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                    modelMap.put("errMsg", "修改成功");
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.put("success", false);
                modelMap.put("errMsg", "ModifyShop Error");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "ShopId不合法");
        }
        return modelMap;
    }

    /**
     * @Description: 从session中获取当前person拥有的商铺列表
     * @Param: request
     * @return: Map<String, Object>
     * @Author: li
     */
    @RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 现在还没有做登录模块，因此session中并没有用户的信息，先模拟一下登录 要改造TODO
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        personInfo.setName("li");
        request.getSession().setAttribute("user", personInfo);
        // 从session中获取user信息
        personInfo = (PersonInfo) request.getSession().getAttribute("user");

        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwner(personInfo);
            ShopExecution se = shopService.getShopList(shopCondition, 1, 99);
            modelMap.put("success", true);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("user", personInfo);
        } catch (ShopOperationException e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    /**
     * @Description: 从商铺列表页面中，点击“进入”按钮进入
     *    某个商铺的管理页面的时候，对session中的数据的校验从而进行页面的跳转，是否跳转到店铺列表页面或者可以直接操作该页面
     *     访问形式如下   http://ip:port/schoolo2o/shopadmin/shopmanagement?shopId=xxx
     * @Param: request
     * @return: Map<String, Object>
     */
    @RequestMapping(value = "/getshopmanageInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopManageInfo(HttpServletRequest request) {
        Map<String,Object> modelMap=new HashMap<>();
        long shopId=HTTPServletRequestUtil.getLong(request,"shopId");
        if (shopId<0){
            Shop currentShop= (Shop) request.getSession().getAttribute("currentShop");
            if (currentShop==null){
                modelMap.put("redirect",true);
                modelMap.put("url","/schooloto/shopadmin/shoplist");
            }else {
                modelMap.put("redirect",false);
                modelMap.put("shopId",currentShop.getShopId());
            }
        }else {
            Shop shop=new Shop();
            shop.setShopId(shopId);

            request.getSession().setAttribute("currentShop",shop);
             modelMap.put("redirect",false);
        }
        return modelMap;
    }
}
