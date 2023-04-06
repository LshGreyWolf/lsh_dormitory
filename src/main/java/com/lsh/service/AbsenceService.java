package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Absence;


/**
 * (Absence)表服务接口
 *
 * @author lsh
 * @since 2023-03-28 17:26:15
 */
public interface AbsenceService extends IService<Absence> {

    int insertAbsence(Absence absence);

    int delete(String ids);

    int updateAbsence(Absence absence);

    Absence getAbsence(Integer id);

    PageInfo<Absence> queryByPage(Absence absence);
}
