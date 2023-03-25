package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Repair;
import com.lsh.mapper.RepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.RepairService;

/**
 * (Repair)表服务实现类
 *
 * @author lsh
 * @since 2023-03-25 17:23:28
 */
@Service("repairService")
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {
        @Autowired
        private RepairMapper repairMapper;
    @Override
    public PageInfo<Repair> queryByPage(Repair repair) {
        if (repair.getPage() != null && repair != null) {
            PageHelper.startPage(repair.getPage(), repair.getLimit());
        }
        return repairMapper.queryByPage(repair);


    }
}
