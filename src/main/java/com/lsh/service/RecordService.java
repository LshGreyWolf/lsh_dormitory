package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Record;


/**
 * (Record)表服务接口
 *
 * @author lsh
 * @since 2023-03-28 16:57:52
 */
public interface RecordService extends IService<Record> {

    PageInfo<Record> queryByPage(Record record);
}
