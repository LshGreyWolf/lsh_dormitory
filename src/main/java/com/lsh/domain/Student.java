package com.lsh.domain;


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
public class Student {
    
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
    private Org org;
    /***
    *学生对应的年级
    */
    private Grade grade;



}
