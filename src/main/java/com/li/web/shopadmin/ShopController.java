package com.li.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.dto.ShopExecution;
import com.li.entity.Area;
import com.li.entity.PersonInfo;
import com.li.entity.Shop;
import com.li.entity.ShopCategory;
import com.li.enums.ShopStateEnum;
import com.li.service.AreaService;
import com.li.service.ShopCategoryService;
import com.li.service.ShopService;
import com.li.util.HTTPServletRequestUtil;
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
     *
     * 1. 接收并转换相应的参数，包括shop信息和图片信息 2. 注册店铺 3. 返回结果给前台
     * @return:Map<String, Object>
     * @Author: li
     */
    @RequestMapping(value = "/registshop",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
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
        // 从request的本次会话中的上线文中获取图片的相关内容
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // shopImg是和前端约定好的变量名
            shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "图片不能为空");
        }

        // 2. 注册店铺
        if (shop != null && shopImg != null) {
            // Session TODO
            // 店主persionInfo的信息，肯定要登录才能注册店铺。
            // 所以这部分信息我们从session中获取，尽量不依赖前端,这里暂时时不具备条件，后续改造，先硬编码，方便单元测试
            PersonInfo personInfo = new PersonInfo();
            personInfo.setUserId(1L);

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
    *@Description:
    *@Param:
    *@return:
    *@Author: li
    */
    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShopInitInfo(){
        Map<String,Object> modelMap=new HashMap<>();
        List<ShopCategory> shopCategoryList=null;
        List<Area> areaList=null;
        try{
            shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList=areaService.getAreaList();
            modelMap.put("success",true);
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }
}
