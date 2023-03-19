package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Org)表实体类
 *
 * @author makejava
 * @since 2023-03-18 14:55:18
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Org  extends Page{
    
    private Integer id;
    
    private String name;
    //1学院/2系/3专业/4班级
    private Integer type;
    
    private Integer gradeId;
    
    private Integer parentId;
    
    private String remark;




}

