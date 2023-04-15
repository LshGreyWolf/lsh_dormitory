package com.lsh.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Grade;
import com.lsh.domain.Org;
import com.lsh.domain.Student;
import com.lsh.service.GradeService;
import com.lsh.service.OrgService;
import com.lsh.service.StudentService;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lsh.constants.SystemConstants.ORG_TYPE_COLLEGE;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/23
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private GradeService gradeService;

    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Student student) {

        //进行分页查询
        PageInfo<Student> studentPageInfo = studentService.queryByPage(student);

        List<Student> studentList = studentPageInfo.getList();
        studentList.forEach(item -> {
            //根据student的id查询对应的班级
            Org org = orgService.detail(item.getClazzId());
            item.setOrg(org);
            //根据student的id查询对应的年级
            Grade grade = gradeService.detail(item.getGradeId());
            item.setGrade(grade);

        });

        return Result.ok(studentPageInfo);
    }

    @PostMapping("/conditionQuery")
    public Map<String, Object> conditionQuery(@RequestBody Student student) {
        //构建分页查询的条件（组织结构 点击事件）
        Integer orgId = student.getOrgId();
        Org detail = orgService.detail(orgId);
        //如果是学院，就将orgId当作学院的id
        if (detail.getParentId() ==ORG_TYPE_COLLEGE){
            student.setCollegeId(orgId);
        }else {
            //如果是专业，就将orgId当作专业的id
            student.setMajorId(orgId);
        }
        //进行分页查询
        PageInfo<Student> studentPageInfo = studentService.queryByPage(student);

        List<Student> studentList = studentPageInfo.getList();
        studentList.forEach(item -> {
            //根据student的id查询对应的班级
            Org org = orgService.detail(item.getClazzId());
            item.setOrg(org);
            //根据student的id查询对应的年级
            Grade grade = gradeService.detail(item.getGradeId());
            item.setGrade(grade);

        });

        return Result.ok(studentPageInfo);
    }
    @GetMapping("delete")
    public Result delete(String ids) {
        int row = studentService.deleteStudent(ids);
        if (row > 0) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/save")
    public Result save(@RequestBody Student student) {
        boolean flag = studentService.saveStudent(student);
        if (flag) {
            return Result.ok("新增成功");
        }
        return Result.fail("新增失败");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Student student) {
        boolean flag = studentService.updateStudent(student);
        if (flag) {
            return Result.ok("修改成功");
        }
        return Result.fail("修改失败");
    }


    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody Student student) {
        Student student1 = new Student();
        student1.setPassword("123456");
        student1.setId(student.getId());
        studentService.updateStudent(student1);
        return Result.ok("重置密码成功！");
    }

    @PostMapping("/returnNull")
    public Result returnNull() {
        return Result.ok();
    }

    /**
     * excel 导入学生数据
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        HashMap<String, String> headerAlias = new HashMap<>(10);
        headerAlias.put("学号", "stuNo");
        headerAlias.put("姓名", "name");
        headerAlias.put("手机号", "phone");
        headerAlias.put("年级", "gradeId");
        headerAlias.put("班级", "clazzId");
        headerAlias.put("性别", "sex");
        headerAlias.put("身份证号", "idcard");
        headerAlias.put("密码", "password");
        headerAlias.put("专业", "majorId");
        headerAlias.put("学院", "collegeId");

        reader.setHeaderAlias(headerAlias);

        List<Student> list = reader.readAll(Student.class);

        System.out.println(list);

        //上传的excel数据,保存到数据库中
        studentService.saveBatch(list);
        return true;
    }

}
