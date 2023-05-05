package com.lsh.domain;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Assess)表实体类
 *
 * @author makejava
 * @since 2023-04-15 11:46:05
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assess  extends Page{
    
    private Integer id;
    
    private Integer userId;
    
    private String description;
    //0-差/1-良好/2-优秀
    private Integer type;
    //宿舍id
    private Integer dormitoryId;
    
    private Date createTime;
    private Date UpdateTime;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Dormitory dormitory;

    private Integer buildingId;




}

