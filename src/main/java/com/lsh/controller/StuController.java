package com.lsh.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.*;
import com.lsh.service.*;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/04/06
 */
@RestController
@RequestMapping("/stu")
@Slf4j
public class StuController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SelectionDormitoryService selectionDormitoryService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private DormitoryStudentService dormitoryStudentService;
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private RepairService repairService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;
    @Autowired
    private SelectionService selectionService;
    @Autowired
    private BuildingService buildingService;

    @PostMapping("/info")
    public Result info() {
        Student param = UserHolder.getStudent();
        Student student = studentService.getStudent(param.getId());
        Org org = orgService.detail(student.getClazzId());
        Grade grade = gradeService.detail(student.getGradeId());
        student.setOrg(org);
        student.setGrade(grade);
        return Result.ok(student);
    }

    /**
     * 查询学生缺勤记录
     *
     * @param absence
     * @param request
     * @return
     */
    @PostMapping("/selectAbsence")
    public Map<String, Object> selectAbsence(@RequestBody Absence absence, HttpServletRequest request) {
        Student param = (Student) request.getAttribute("student");
        absence.setStudentId(param.getId());
        PageInfo<Absence> pageInfo = absenceService.queryByPage(absence);
        pageInfo.getList().forEach(entity -> {
            Student detail = studentService.getStudent(entity.getStudentId());
            entity.setStudent(detail);
            LambdaQueryWrapper<Dormitory> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Dormitory::getId, entity.getDormitoryId());
            Dormitory dormitory = dormitoryService.getOne(queryWrapper);
            entity.setDormitory(dormitory);
        });
        return Result.ok(pageInfo);
    }

    /**
     * 在线选宿舍（查询）
     *
     * @return
     */
    @PostMapping("/selectDormitory")
    public Result selectDormitory() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        try {
            Student entity = UserHolder.getStudent();
            Student student = studentService.getStudent(entity.getId());
            SelectionDormitory selectionDormitory = new SelectionDormitory();
            selectionDormitory.setClazzId(student.getClazzId());
            PageInfo<SelectionDormitory> selectionDormitoryPageInfo = selectionDormitoryService.querySelectionDormitory(selectionDormitory);
            List<SelectionDormitory> selectionDormitoryList = selectionDormitoryPageInfo.getList();
            for (SelectionDormitory dormitory : selectionDormitoryList) {
                HashMap<String, Object> map = new HashMap<>();
                //查出宿舍的容量,宿舍号，以及性别
                Dormitory dormitory1 = dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, dormitory.getDormitoryId()));
                map.put("capacity", dormitory1.getCapacity());
                map.put("id", dormitory1.getId());
                map.put("no", dormitory1.getNo());
                map.put("sex", dormitory1.getSex());
                Building building1 = new Building();
                building1.setId(dormitory1.getBuildingId());
                Building building = buildingService.getBuilding(building1);
                map.put("buildingName", building.getName());
                //查询已选择的所有学生的个数
                int count = dormitoryStudentService.count(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getDormitoryId, dormitory.getDormitoryId()));
                //查询已选择的所有学生的列表
                List<DormitoryStudent> studentList = dormitoryStudentService.list(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getDormitoryId, dormitory.getDormitoryId()));
                map.put("selectCount", count);
                List<Map<String, Object>> studentMapList = new ArrayList<>();
                //循环已选学生列表得到已选学生的宿舍号和姓名和床位id
                studentList.forEach(student1 -> {
                    Map<String, Object> studentMap = new HashMap<>();
                    Student studentDetail = studentService.getStudent(student1.getStudentId());
                    studentMap.put("stuNo", studentDetail.getStuNo());
                    studentMap.put("name", studentDetail.getName());
                    studentMap.put("bedId", student1.getBedId());
                    studentMapList.add(studentMap);
                });
                map.put("studentList", studentMapList);
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("系统异常，请稍后再试");
        }
        return Result.ok(list);
    }

    /**
     * 选择
     *
     * @param map
     * @return
     */
    @PostMapping("/selectDormitorySubmit")
    public Result selectDormitorySubmit(@RequestBody Map<String, String> map) {
        Student param = UserHolder.getStudent();
        Student student = studentService.getStudent(param.getId());
        String bedId = map.get("bedId"); String dormitoryId = map.get("dormitoryId");
        List<Selection> selections = selectionService.selectByClazzId(student.getClazzId());
        if (selections == null || selections.size() == 0) {
            return Result.fail("操作失败，未设置！请联系管理员");  }
        Selection selection = selections.get(0);
        if (selection.getStartTime().getTime() > System.currentTimeMillis()
                || System.currentTimeMillis() > selection.getEndTime().getTime()) {
            return Result.fail("操作失败，不在时间段内选择");}
        Integer sex = student.getSex(); //查询登录学生的性别
        Dormitory dormitory = dormitoryService.getById(dormitoryId);
        if (!Objects.equals(sex, dormitory.getSex())) {
            return Result.fail("请选择正确的宿舍");
        }int row;  try {
            row = dormitoryStudentService.selectDormitorySubmit(student.getId(), Integer.parseInt(dormitoryId), Integer.parseInt(bedId));
                if (row == 0) {   return Result.fail("选择失败，请稍后重试"); }
        } catch (Exception e) {
            return Result.fail("版本冲突！,请稍后再试");
        } return Result.ok("选择成功！");
    }

    /**
     * 报修申请
     *
     * @param repair
     * @return
     */
    @PostMapping("/addRepair")
    public Result addRepair(@RequestBody Repair repair) {
        Student student = UserHolder.getStudent();
        String description = repair.getDescription();
        List<DormitoryStudent> list =
                dormitoryStudentService.list(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getStudentId, student.getId()));
        if (list != null && list.size() > 0) {
            DormitoryStudent dormitoryStudent = list.get(0);
            Dormitory dormitory =
                    dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, dormitoryStudent.getDormitoryId()));
            repair.setDormitoryId(dormitory.getId());
            repair.setBuildingId(dormitory.getBuildingId());
            repair.setCreateDate(new Date());
            repair.setDescription(description);
            repair.setStudentId(student.getId());
            //待解决
            repair.setStatus(0);
            boolean flag = repairService.saveRepair(repair);
            if (!flag) {
                return Result.fail("新增失败！");
            }
        } else {
            return Result.fail("操作失败。无关联的宿舍。");
        }
        return Result.ok("新增成功！");
    }

    @PostMapping("/selectNotice")
    public Map<String, Object> selectNotice(@RequestBody Notice notice) {
        Student student = UserHolder.getStudent();
        //根据学生id查询 出 学生所选的宿舍
        List<DormitoryStudent> list =
                dormitoryStudentService.list(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getStudentId, student.getId()));
        PageInfo<Notice> noticePageInfo = null;
        if (list != null && list.size() > 0) {
            DormitoryStudent dormitoryStudent = list.get(0);
            //得到学生所选宿舍的详细信息
            Dormitory dormitory = dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, dormitoryStudent.getDormitoryId()));
            notice.setBuildingId(dormitory.getBuildingId());
            noticePageInfo = noticeService.queryByBuildingId(notice);
            List<Notice> noticeList = noticePageInfo.getList();
            noticeList.forEach(i -> {
                User user = new User();
                user.setId(i.getUserId());
                i.setUser(userService.getUser(user));
            });
            return Result.ok(noticePageInfo);
        } else {
            return Result.ok(noticePageInfo);
        }
    }

    @PostMapping("/updateStudent")
    public Result updateStudent(@RequestBody Student student) {
        Integer id = UserHolder.getStudent().getId();
        student.setId(id);
        studentService.updateById(student);
        return Result.ok("修改成功！");
    }

}
