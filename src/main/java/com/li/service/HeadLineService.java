package com.li.service;

import com.li.entity.HeadLine;

import java.util.List;

/**
 * @ClassName: HeadLineService
 * @Description:
 * @author: libl
 * @date: 2019/06/28 11:05
 */
public interface HeadLineService {

    /**
     * @Description: 查询headLine
     * @Param: headLineConditon
     * @return:
     */
    List<HeadLine> queryHeadLineList(HeadLine headLineCondition);
}
