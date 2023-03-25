package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Repair;


/**
 * (Repair)表服务接口
 *
 * @author lsh
 * @since 2023-03-25 17:23:28
 */
public interface RepairService extends IService<Repair> {

    PageInfo<Repair> queryByPage(Repair repair);
}
