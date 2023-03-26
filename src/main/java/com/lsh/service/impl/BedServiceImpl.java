package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Bed;
import com.lsh.mapper.BedMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.BedService;

/**
 * (Bed)表服务实现类
 *
 * @author makejava
 * @since 2023-03-26 15:18:56
 */
@Service("bedService")
public class BedServiceImpl extends ServiceImpl<BedMapper, Bed> implements BedService {

}

