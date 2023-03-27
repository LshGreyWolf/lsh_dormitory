package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Visit;


/**
 * (Visit)表服务接口
 *
 * @author lsh
 * @since 2023-03-27 20:26:13
 */
public interface VisitService extends IService<Visit> {

    PageInfo<Visit> queryByPage(Visit visit);
}
