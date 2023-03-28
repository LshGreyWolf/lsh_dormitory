package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.SelectionJoiner;
import com.lsh.mapper.SelectionJoinerMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.SelectionJoinerService;

/**
 * (SelectionJoiner)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:25:14
 */
@Service("selectionJoinerService")
public class SelectionJoinerServiceImpl extends ServiceImpl<SelectionJoinerMapper, SelectionJoiner> implements SelectionJoinerService {

}
