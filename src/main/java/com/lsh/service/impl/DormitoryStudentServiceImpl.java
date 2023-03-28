package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.DormitoryStudent;
import com.lsh.mapper.DormitoryStudentMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.DormitoryStudentService;

/**
 * (DormitoryStudent)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:28:10
 */
@Service("dormitoryStudentService")
public class DormitoryStudentServiceImpl extends ServiceImpl<DormitoryStudentMapper, DormitoryStudent> implements DormitoryStudentService {

}
