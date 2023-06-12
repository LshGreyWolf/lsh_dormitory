package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.DormitorySet;


/**
 * (DormitorySet)表服务接口
 *
 * @author lsh
 * @since 2023-03-28 17:27:15
 */
public interface DormitorySetService extends IService<DormitorySet> {

    boolean saveDormitorySet(DormitorySet dormitorySet);

    PageInfo<DormitorySet> queryDormitorySet(DormitorySet dormitorySet);

    int deleteDormitorySet(String ids);

    int updateDormitorySet(DormitorySet dormitorySet);
}
