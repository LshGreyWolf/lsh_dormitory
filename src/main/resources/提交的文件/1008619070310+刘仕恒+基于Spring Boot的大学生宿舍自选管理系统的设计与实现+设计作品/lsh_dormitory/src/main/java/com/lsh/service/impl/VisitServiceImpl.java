package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Visit;
import com.lsh.mapper.VisitMapper;
import com.sun.corba.se.spi.ior.IdentifiableFactory;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.VisitService;

/**
 * (Visit)表服务实现类
 *
 * @author lsh
 * @since 2023-03-27 20:26:13
 */
@Service("visitService")
public class VisitServiceImpl extends ServiceImpl<VisitMapper, Visit> implements VisitService {
    @Autowired
    private VisitMapper visitMapper;

    @Override
    public PageInfo<Visit> queryByPage(Visit visit) {
        if (visit != null && visit.getLimit() != null) {
            PageHelper.startPage(visit.getPage(), visit.getLimit());

        }
        return new PageInfo<Visit>(visitMapper.queryByPage(visit));


    }

    @Override
    public int saveVisitors(Visit visit) {


        return visitMapper.saveVisitors(visit);
    }

    @Override
    public boolean updateVisitor(Visit visit) {

        return visitMapper.updateVisitor(visit);
    }

    @Override
    public int deleteVisitor(String ids) {
        String[] idArr = ids.split(",");
        int row = 0;
        for (String id : idArr) {
            visitMapper.deleteById(id);
            row++;
        }
        return row;
    }
}
