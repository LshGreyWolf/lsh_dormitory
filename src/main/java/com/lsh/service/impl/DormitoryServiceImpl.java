package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Dormitory;
import com.lsh.mapper.DormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.DormitoryService;

import java.util.List;

/**
 * (Dormitory)表服务实现类
 *
 * @author makejava
 * @since 2023-03-26 14:36:18
 */
@Service("dormitoryService")
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {

    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Override
    public List<Dormitory> listNo() {
        return dormitoryMapper.listNo();
    }
}

