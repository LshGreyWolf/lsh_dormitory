package com.lsh.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsh.domain.Bed;
import com.lsh.domain.Dormitory;
import com.lsh.domain.DormitoryStudent;
import com.lsh.domain.Storey;
import com.lsh.domain.Vo.ReplaceBedDto;
import com.lsh.service.BedService;
import com.lsh.service.DormitoryService;
import com.lsh.service.DormitoryStudentService;
import com.lsh.service.StoreyService;
import com.lsh.utils.RedisCache;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.lsh.constants.RedisConstants.STUDENT_DORMITORY;

/**
 * @author lsh
 * @version 1.0
 * @description TODO
 * @date 2023/3/26 14:37
 */
@RestController
@RequestMapping("/dormitory")
public class DormitoryController {

    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private StoreyService storeyService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private BedService bedService;
    @Autowired
    private DormitoryStudentService dormitoryStudentService;

    /**
     * 根据楼层id 查询宿舍信息
     * @param storeyId 楼层id
     * @return
     */
    @PostMapping("/list/{storeyId}")
    public Result list(@PathVariable("storeyId") Integer storeyId) {

        LambdaQueryWrapper<Dormitory> queryWrapper = new LambdaQueryWrapper<Dormitory>()
                .eq(Dormitory::getStoreyId, storeyId);
        List<Dormitory> dormitoryList = dormitoryService.list(queryWrapper);
        Storey storey = storeyService.getOne(new LambdaQueryWrapper<Storey>().eq(Storey::getId, storeyId));
        //将楼层对应的宿舍缓存到redis
        String key = STUDENT_DORMITORY + storey.getName();
        redisCache.setCacheObject(key, JSONUtil.toJsonStr(dormitoryList));

        return Result.ok(dormitoryList);
    }

    @PostMapping("save")
    public Result save(@RequestBody Dormitory dormitory) {
        //先查询该栋楼现存在的所有宿舍
        List<Dormitory> dormitoryList = dormitoryService.listDormitory(dormitory);
        for (Dormitory item : dormitoryList) {
            if (item.getNo().equals(dormitory.getNo())) {
                return Result.fail("该栋楼的该宿舍已经存在，请选择其他宿舍号");
            }
        }
        boolean flag = dormitoryService.save(dormitory);
        if (flag) {
            return Result.ok("新增成功");
        }
        return Result.ok("新增失败");
    }


    @GetMapping("/delete")
    public Result delete(Integer dormitoryId) {
        boolean flag = dormitoryService.removeById(dormitoryId);
        if (flag) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/list")
    public Result list(@RequestBody Dormitory dormitory) {
        List<Dormitory> dormitories = dormitoryService.listDormitory(dormitory);
        return Result.ok(dormitories);
    }

    @PostMapping("/initDormitory")
    public Result initDormitory(@RequestBody Dormitory dormitory) {
        dormitoryService.initDormitory(dormitory);
        return Result.ok("初始化成功！");
    }

    @PostMapping("/replaceBed")
    public Result replaceBed(@RequestBody ReplaceBedDto replaceBedDto) {
        Integer gradeId = replaceBedDto.getGradeId();
        Integer clazzId = replaceBedDto.getClazzId();
        Integer collegeId = replaceBedDto.getCollegeId();
        Integer majorId = replaceBedDto.getMajorId();
        Integer studentId = replaceBedDto.getStudentId();
        Integer bedId = replaceBedDto.getBedId();

        //调换床位id
        //根据床位id查出之前的学生的信息
        DormitoryStudent originalDormitoryStudent =
                dormitoryStudentService.getOne(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getBedId, bedId));
        //删除原来学生的床位和选宿舍的信息（dormitory  dormitory_student）
        boolean flag = dormitoryStudentService.remove(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getStudentId, originalDormitoryStudent.getStudentId()));
        //插入新学生的信息

        if (!flag){
            return Result.fail("系统异常！");
        }
        DormitoryStudent dormitoryStudent = new DormitoryStudent();
        dormitoryStudent.setBedId(bedId);
        dormitoryStudent.setStudentId(studentId);
        dormitoryStudent.setCheckin(new Date());
        dormitoryStudent.setStatus(1);
        Bed bed = bedService.getOne(new LambdaQueryWrapper<Bed>().eq(Bed::getId, bedId));
        dormitoryStudent.setDormitoryId(bed.getDormitoryId());
        dormitoryStudentService.save(dormitoryStudent);
        return Result.ok("调换成功！");
    }

}
