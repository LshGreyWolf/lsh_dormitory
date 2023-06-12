package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Storey;
import com.lsh.mapper.StoreyMapper;
import com.lsh.service.StoreyService;
import org.springframework.stereotype.Service;

/**
 * (Storey)表服务实现类
 *
 * @author makejava
 * @since 2023-03-26 13:14:24
 */
@Service("storeyService")
public class StoreyServiceImpl extends ServiceImpl<StoreyMapper, Storey> implements StoreyService {

}

