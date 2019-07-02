package com.li.service;

import com.li.BaseTest;
import com.li.entity.HeadLine;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: HeadLineServiceTest
 * @Description:
 * @author: libl
 * @date: 2019/06/28 11:19
 */

public class HeadLineServiceTest extends BaseTest {

    @Autowired
    private HeadLineService headLineService;

    @Test
    public void testQueryHeadLineList(){
        HeadLine headLineCondition=new HeadLine();
        // 状态 0 不可用 1 可用
        headLineCondition.setEnableStatus(0);

        // 查询不可用的头条信息
        List<HeadLine> headLineList = headLineService.queryHeadLineList(headLineCondition);
        System.out.println(headLineList.size());
        for (HeadLine headLine:headLineList)
            System.out.println(headLine);
        System.out.println("-----------------------");
        // 查询可用的头条信息
        headLineCondition.setEnableStatus(1);
        headLineList=headLineService.queryHeadLineList(headLineCondition);
        System.out.println(headLineList.size());
        for (HeadLine headLine:headLineList)
            System.out.println(headLine);
        System.out.println("-----------------------");

    }
}
