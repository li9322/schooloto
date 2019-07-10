package com.li.service;

import com.li.BaseTest;
import com.li.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList);

    }

    @Test
    public void t2(){
       List<Integer> list=new ArrayList<>();
       list.add(6);
       list.add(5);
       list.add(1);
       list.add(4);
       list.add(2);
       removeEvensVer2(list);
    }
    public void removeEvensVer2(List<Integer> lst){
        for (Integer x:lst)
            if (x%2==0)
                lst.remove(x);
    }
}
