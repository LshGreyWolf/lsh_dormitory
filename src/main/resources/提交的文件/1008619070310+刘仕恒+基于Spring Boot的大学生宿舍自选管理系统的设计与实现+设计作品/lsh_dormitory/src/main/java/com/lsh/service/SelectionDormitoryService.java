package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.SelectionDormitory;


/**
 * (SelectionDormitory)表服务接口
 *
 * @author lsh
 * @since 2023-03-28 17:24:13
 */
public interface SelectionDormitoryService extends IService<SelectionDormitory> {

    int updateSelectionDormitory(SelectionDormitory selectionDormitory);

    int saveSelectionDormitory(String clazzId, String dormitoryIds);

    PageInfo<SelectionDormitory> querySelectionDormitory(SelectionDormitory selectionDormitory);
}
