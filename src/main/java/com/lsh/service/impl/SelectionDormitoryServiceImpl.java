package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.SelectionDormitory;
import com.lsh.mapper.SelectionDormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.SelectionDormitoryService;
import org.springframework.util.StringUtils;

/**
 * (SelectionDormitory)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:24:13
 */
@Service("selectionDormitoryService")
public class SelectionDormitoryServiceImpl extends ServiceImpl<SelectionDormitoryMapper, SelectionDormitory> implements SelectionDormitoryService {
    @Autowired
    private SelectionDormitoryMapper selectionDormitoryMapper;

    @Override

    public int updateSelectionDormitory(SelectionDormitory selectionDormitory) {

        return 1;
    }

    @Override
    public int saveSelectionDormitory(String clazzId, String dormitoryIds) {
        String[] ids = dormitoryIds.split(",");
        //先删除原来的设置
        selectionDormitoryMapper.deleteByClazzId(Integer.parseInt(clazzId));

        for (String id : ids) {
            if (!StringUtils.isEmpty(id)) {
                SelectionDormitory selectionDormitory = new SelectionDormitory();
                selectionDormitory.setClazzId(Integer.parseInt(clazzId));
                selectionDormitory.setDormitoryId(Integer.parseInt(id));
                selectionDormitoryMapper.saveSelectionDormitory(selectionDormitory);
            }
        }
        return 1;


    }

    @Override
    public PageInfo<SelectionDormitory> querySelectionDormitory(SelectionDormitory selectionDormitory) {

        if(selectionDormitory != null && selectionDormitory.getPage() != null){
            PageHelper.startPage(selectionDormitory.getPage(),selectionDormitory.getLimit());
        }
        return new PageInfo<SelectionDormitory>(selectionDormitoryMapper.querySelectionDormitory(selectionDormitory));
    }
}
