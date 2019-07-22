package com.li.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.li.entity.Area;

import java.io.IOException;
import java.util.List;

public interface AreaService {
    // redis key的前缀，抽取到接口层，方便使用
    public static final String AREALISTKEY = "arealist";

    /**
     * @Description: getAreaList
     * @Param: 获取区域列表
     * @return: List<Area>
     */
    List<Area> getAreaList() throws JsonParseException, JsonMappingException, IOException;

}
