package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.SelectionDormitory;
import com.lsh.mapper.SelectionDormitoryMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.SelectionDormitoryService;

/**
 * (SelectionDormitory)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:24:13
 */
@Service("selectionDormitoryService")
public class SelectionDormitoryServiceImpl extends ServiceImpl<SelectionDormitoryMapper, SelectionDormitory> implements SelectionDormitoryService {

}
