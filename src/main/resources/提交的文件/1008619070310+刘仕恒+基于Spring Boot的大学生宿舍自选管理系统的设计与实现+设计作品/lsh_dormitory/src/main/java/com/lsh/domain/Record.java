package com.lsh.domain;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Record)表实体类
 *
 * @author lsh
 * @since 2023-03-28 16:57:52
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record extends Page{

    private Integer id;

    private Integer studentId;

    private Integer dormitoryId;

    private Integer bedId;
    //1入住/2退宿
    private Integer status;

    private Date createDate;
    @TableField(exist = false)
    private Student student;
    @TableField(exist = false)
    private Dormitory dormitory;
    @TableField(exist = false)
    private Bed bed;


}
