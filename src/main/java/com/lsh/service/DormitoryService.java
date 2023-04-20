package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Dormitory;
import com.lsh.domain.Notice;

import java.util.List;


/**
 * (Dormitory)表服务接口
 *
 * @author makejava
 * @since 2023-03-26 14:36:18
 */
public interface DormitoryService extends IService<Dormitory> {

    List<Dormitory> listNo();

    List<Dormitory> listDormitory(Dormitory dormitory);

    void initDormitory(Dormitory dormitory);

    int getVersion(Integer id);


    int selectCountDormitory(Integer buildingId);
}

