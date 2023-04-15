package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Assess;


/**
 * (Assess)表服务接口
 *
 * @author makejava
 * @since 2023-04-15 11:46:05
 */
public interface AssessService extends IService<Assess> {

    PageInfo<Assess> queryByPage(Assess assess);

    int deleteAssess(String ids);
}

