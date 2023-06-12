package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.DormitoryStudent;
import com.lsh.domain.Student;


/**
 * (DormitoryStudent)表服务接口
 *
 * @author lsh
 * @since 2023-03-28 17:28:09
 */
public interface DormitoryStudentService extends IService<DormitoryStudent> {

    int selectDormitorySubmit(Integer studentId,Integer dormitoryId,Integer bedId) throws Exception;


    int countByBuildingId(Integer buildingId);


    Student queryStudentByBedId(Integer bedId);
}
