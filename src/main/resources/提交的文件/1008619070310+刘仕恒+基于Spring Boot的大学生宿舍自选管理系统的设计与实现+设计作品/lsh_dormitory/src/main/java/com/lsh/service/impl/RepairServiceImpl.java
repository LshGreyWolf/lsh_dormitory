package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Repair;
import com.lsh.mapper.RepairMapper;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.RepairService;
import org.springframework.util.StringUtils;

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
        return new PageInfo<Repair>(repairMapper.queryByPage(repair));


    }

    @Override
    public int updateRepair(Repair repair) {
        return repairMapper.updateRepair(repair);
    }

    @Override
    public int deleteRepair(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if (!StringUtils.isEmpty(s)) {
                Repair repair = repairMapper.selectById(s);
                Integer status = repair.getStatus();
                if (status == 0) {
                    return -1;
                }
                repairMapper.deleteById(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    @Override
    public boolean saveRepair(Repair repair) {

        return repairMapper.saveRepair(repair);


    }
}
