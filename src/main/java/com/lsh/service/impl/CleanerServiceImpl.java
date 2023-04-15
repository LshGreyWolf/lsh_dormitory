package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Absence;
import com.lsh.domain.Cleaner;
import com.lsh.mapper.CleanerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.CleanerService;

/**
 * (Cleaner)表服务实现类
 *
 * @author makejava
 * @since 2023-04-15 10:04:45
 */
@Service("cleanerService")
public class CleanerServiceImpl extends ServiceImpl<CleanerMapper, Cleaner> implements CleanerService {
    @Autowired
    private CleanerMapper cleanerMapper;

    @Override
    public PageInfo<Cleaner> queryByPage(Cleaner cleaner) {

        if (cleaner != null && cleaner.getPage() != null) {
            PageHelper.startPage(cleaner.getPage(), cleaner.getLimit());
        }
        return new PageInfo<Cleaner>(cleanerMapper.queryByPage(cleaner));
    }

    @Override
    public int deleteCleaner(String ids) {
        String[] idsArr = ids.split(",");
        int row = 0;
        for (String id : idsArr) {
            cleanerMapper.deleteById(id);
            row++;
        }
        return row;


    }
}

