package com.li.dao;

import com.li.BaseTest;
import com.li.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @ClassName: HeadLineDaoTest
 * @Description:
 * @author: libl
 * @date: 2019/06/27 16:18
 */
public class HeadLineDaoTest extends BaseTest {


    @Autowired
    HeadLineDao headLineDao;

    @Test
    public void testSelectHeadLineList() {
        HeadLine headLineCondition = new HeadLine();
        //状态 0不可用 1可用
        headLineCondition.setEnableStatus(0);

        // 查询不可用的头条信息
        List<HeadLine> headLineList = headLineDao.selectHeadLineList(headLineCondition);
        System.out.println(headLineList.size());
        for (HeadLine headLine:headLineList)
            System.out.println(headLine);
        System.out.println("-----------------------");
        // 查询可用的头条信息
        headLineCondition.setEnableStatus(1);
        headLineList=headLineDao.selectHeadLineList(headLineCondition);
        System.out.println(headLineList.size());
        for (HeadLine headLine:headLineList)
            System.out.println(headLine);
        System.out.println("-----------------------");
        // 查询全部状态的头条信息
        headLineList=headLineDao.selectHeadLineList(new HeadLine());
        System.out.println(headLineList.size());
        for (HeadLine headLine:headLineList)
            System.out.println(headLine);
    }
}
