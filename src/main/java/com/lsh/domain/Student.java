package com.lsh.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (TbStudent)表实体类
 *
 * @author lsh
 * @since 2023-03-06 09:20:40
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Page {

    private Integer id;

    private String stuNo;

    private String name;

    private String idcard;

    private Integer gradeId;

    private Integer sex;

    private String phone;

    private String password;

    private Integer clazzId;
    /***
     *  学生对应的组织结构
     */
    @TableField(exist = false)
    private Org org;
    /***
     *学生对应的年级
     */
    @TableField(exist = false)
    private Grade grade;

    /***
     *专业id
     */
    private Integer majorId;
    /**
     * 学院id
     */
    private Integer collegeId;
    /**
     * 用来比较前端传来的id 为专业或者学院
     */
    @TableField(exist = false)
    private Integer orgId;


}
