package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Cleaner;


/**
 * (Cleaner)表服务接口
 *
 * @author makejava
 * @since 2023-04-15 10:04:44
 */
public interface CleanerService extends IService<Cleaner> {

    PageInfo<Cleaner> queryByPage(Cleaner cleaner);

    int deleteCleaner(String ids);
}

