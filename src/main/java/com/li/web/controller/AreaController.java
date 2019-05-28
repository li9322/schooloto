package com.li.web.controller;

import com.li.entity.Area;
import com.li.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listArea(){
        logger.info("-----begin getAreas------");
        Long beginTimeLong = System.currentTimeMillis();

        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Area> list =new ArrayList<>();
        try {
            list=areaService.getAreaList();
            modelMap.put("rows",list);
            modelMap.put("total",list.size());

            for (Area area: list)
                System.out.println("区域：" + area);
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            logger.error("exception happpens , desc [{}] ", e.getMessage());
        }

        Long endTimeLong = System.currentTimeMillis();
        logger.debug("cost [{}ms]", endTimeLong - beginTimeLong);
        logger.info("-----end getAreas------");
        return modelMap;
    }
}
