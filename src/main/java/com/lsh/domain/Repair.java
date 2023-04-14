package com.lsh.domain;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Repair)表实体类
 *
 * @author lsh
 * @since 2023-03-25 17:23:28
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repair extends Page {

    private Integer id;

    private Integer studentId;

    private Integer dormitoryId;

    private Integer buildingId;

    private String description;

    private Date createDate;
    //0待解决/1已解决
    private Integer status;

    @TableField(exist = false)
    private Student student;

    @TableField(exist = false)
    private Dormitory dormitory;

    @TableField(exist = false)
    private Building building;
    /***
     *审核人
     */
    private String processor;
    /***
     *审核意见
     */
    private String processIdea;


}
