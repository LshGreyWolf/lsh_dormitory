package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Assess;
import com.lsh.domain.Cleaner;
import com.lsh.mapper.AssessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.AssessService;

/**
 * (Assess)表服务实现类
 *
 * @author makejava
 * @since 2023-04-15 11:46:05
 */
@Service("assessService")
public class AssessServiceImpl extends ServiceImpl<AssessMapper, Assess> implements AssessService {
    @Autowired
    AssessMapper assessMapper;

    @Override
    public PageInfo<Assess> queryByPage(Assess assess) {


        if (assess != null && assess.getPage() != null) {
            PageHelper.startPage(assess.getPage(), assess.getLimit());
        }
        return new PageInfo<Assess>(assessMapper.queryByPage(assess));
    }

    @Override
    public int deleteAssess(String ids) {
        String[] idsArr = ids.split(",");
        int row = 0;
        for (String id : idsArr) {
            assessMapper.deleteById(id);
            row++;
        }
        return row;
    }
}

