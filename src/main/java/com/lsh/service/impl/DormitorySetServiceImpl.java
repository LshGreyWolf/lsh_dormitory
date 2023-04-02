package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.DormitorySet;
import com.lsh.mapper.DormitorySetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.DormitorySetService;
import org.springframework.util.StringUtils;

/**
 * (DormitorySet)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:27:15
 */
@Service("dormitorySetService")
public class DormitorySetServiceImpl extends ServiceImpl<DormitorySetMapper, DormitorySet> implements DormitorySetService {
@Autowired
private DormitorySetMapper dormitorySetMapper;
    @Override
    public boolean saveDormitorySet(DormitorySet dormitorySet) {

        return dormitorySetMapper.saveDormitorySet(dormitorySet);
    }

    @Override
    public PageInfo<DormitorySet> queryDormitorySet(DormitorySet dormitorySet) {
        if(dormitorySet != null && dormitorySet.getPage() != null){
            PageHelper.startPage(dormitorySet.getPage(),dormitorySet.getLimit());
        }
        return new PageInfo<DormitorySet>(dormitorySetMapper.queryDormitorySet(dormitorySet));

    }

    @Override
    public int deleteDormitorySet(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String id : arr) {
            if(!StringUtils.isEmpty(id)){
                dormitorySetMapper.deleteById(Integer.parseInt(id));
                row++;
            }
        }
        return row;

    }

    @Override
    public int updateDormitorySet(DormitorySet dormitorySet) {

        return dormitorySetMapper.updateDormitorySet(dormitorySet);
    }
}
