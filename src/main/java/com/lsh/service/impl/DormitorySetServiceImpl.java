package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.DormitorySet;
import com.lsh.mapper.DormitorySetMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.DormitorySetService;

/**
 * (DormitorySet)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:27:15
 */
@Service("dormitorySetService")
public class DormitorySetServiceImpl extends ServiceImpl<DormitorySetMapper, DormitorySet> implements DormitorySetService {

}
