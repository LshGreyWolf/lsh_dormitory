package com.lsh.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Record;
import com.lsh.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.RecordService;

/**
 * (Record)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 16:57:52
 */
@Service("recordService")
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public PageInfo<Record> queryByPage(Record record) {
        if (record != null && record.getLimit() != null) {
            PageHelper.startPage(record.getPage(), record.getLimit());
        }
        return new PageInfo<Record>(recordMapper.queryByPage(record));


    }
}
