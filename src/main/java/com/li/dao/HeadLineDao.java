package com.li.dao;

import com.li.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: HeadLineDao
 * @Description:
 * @author: libl
 * @date: 2019/06/27 15:44
 */
public interface HeadLineDao {
    /**
     * @Description: 根据enable_status查询符合条件的头条信息
     * @Param: headLineConditon
     * @return: List<HeadLine>
     */
    List<HeadLine> selectHeadLineList(@Param("headLineCondition") HeadLine headLineCondition);
}
