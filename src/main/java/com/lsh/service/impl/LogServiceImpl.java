package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Absence;
import com.lsh.domain.LogEntity;
import com.lsh.mapper.AbsenceMapper;
import com.lsh.mapper.LogServiceMapper;
import com.lsh.service.AbsenceService;
import com.lsh.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogServiceMapper, LogEntity> implements LogService {
}
