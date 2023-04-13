package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Selection;


/**
 * (Selection)表服务接口
 *
 * @author lsh
 * @since 2023-03-28 17:22:50
 */
public interface SelectionService extends IService<Selection> {

    PageInfo<Selection> querySelection(Selection selection);

    void saveSelection(Selection selection);

    void updateSelection(Selection selection);

    boolean deleteSelection(String ids);
}
