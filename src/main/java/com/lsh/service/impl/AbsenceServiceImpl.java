package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Absence;
import com.lsh.mapper.AbsenceMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.AbsenceService;

/**
 * (Absence)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:26:15
 */
@Service("absenceService")
public class AbsenceServiceImpl extends ServiceImpl<AbsenceMapper, Absence> implements AbsenceService {

}
