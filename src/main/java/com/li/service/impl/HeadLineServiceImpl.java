package com.li.service.impl;

import com.li.dao.HeadLineDao;
import com.li.entity.HeadLine;
import com.li.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: HeadLineServiceImpl
 * @Description:
 * @author: libl
 * @date: 2019/06/28 11:16
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    HeadLineDao headLineDao;

    @Override
    public List<HeadLine> queryHeadLineList(HeadLine headLineCondition) {
        return headLineDao.selectHeadLineList(headLineCondition);
    }
}
