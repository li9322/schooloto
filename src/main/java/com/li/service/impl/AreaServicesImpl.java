package com.li.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.cache.JedisUtil;
import com.li.dao.AreaDao;
import com.li.entity.Area;
import com.li.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AreaServicesImpl
 * @Description: @Service标注的服务层
 * @author: libl
 * @date: 2019/07/22 13:28
 */

@Service
public class AreaServicesImpl implements AreaService {
    @Autowired
    AreaDao areaDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedistKeys;

    private static String AREALISTKEY = "arealist";

    @Override
    public List<Area> getAreaList() throws JsonParseException, JsonMappingException, IOException {
        // 定义redis中的key
        String key = AREALISTKEY;
        // 定义接收对象
        List<Area> areaList = null;
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        // 判断key是否存在
        if (!jedistKeys.exists(key)) {
            areaList = areaDao.queryArea();
            String jsonString = mapper.writeValueAsString(areaList);
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            areaList = mapper.readValue(jsonString, javaType);
        }
        return areaList;
    }
}
