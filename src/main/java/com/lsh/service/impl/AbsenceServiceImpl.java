package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Absence;
import com.lsh.mapper.AbsenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.AbsenceService;
import org.springframework.util.StringUtils;

/**
 * (Absence)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:26:15
 */
@Service("absenceService")
public class AbsenceServiceImpl extends ServiceImpl<AbsenceMapper, Absence> implements AbsenceService {
    @Autowired
    private AbsenceMapper absenceMapper;

    @Override
    public int insert(Absence absence) {

        return absenceMapper.insert(absence);
    }

    @Override
    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if(!StringUtils.isEmpty(s)){
                absenceMapper.deleteById(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    @Override
    public int updateAbsence(Absence absence) {
        return absenceMapper.updateAbsence(absence);
    }

    @Override
    public Absence getAbsence(Integer id) {
        return absenceMapper.getAbsence(id);
    }

    @Override
    public PageInfo<Absence> queryByPage(Absence absence) {
        if(absence != null && absence.getPage() != null){
            PageHelper.startPage(absence.getPage(),absence.getLimit());
        }
        return new PageInfo<Absence>(absenceMapper.queryByPage(absence));
    }
}
