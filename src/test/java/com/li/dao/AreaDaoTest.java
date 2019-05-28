package com.li.dao;

import com.li.BaseTest;
import com.li.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList =areaDao.queryArea();
        System.out.println(areaList);
    }


}
