package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Selection;
import com.lsh.mapper.SelectionMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.SelectionService;

/**
 * (Selection)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:22:50
 */
@Service("selectionService")
public class SelectionServiceImpl extends ServiceImpl<SelectionMapper, Selection> implements SelectionService {

}
