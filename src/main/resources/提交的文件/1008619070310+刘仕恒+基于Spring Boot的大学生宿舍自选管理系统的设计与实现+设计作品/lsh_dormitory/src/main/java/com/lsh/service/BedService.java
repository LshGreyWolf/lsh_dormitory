package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsh.domain.Bed;

import java.util.List;


/**
 * (Bed)表服务接口
 *
 * @author makejava
 * @since 2023-03-26 15:18:56
 */
public interface BedService extends IService<Bed> {

    List<Bed> listBed(Bed bed);

}

