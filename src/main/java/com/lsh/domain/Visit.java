package com.lsh.domain;

import java.util.Date;

import java.io.Serializable;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Visit)表实体类
 *
 * @author lsh
 * @since 2023-03-27 20:26:13
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visit extends Page {
    
    private Integer id;
    
    private String visitor;
    
    private String phone;
    
    private Integer sex;
    
    private String idcard;
    
    private Integer studentId;
    
    private String visitTime;
    
    private String leaveTime;
    
    private String remark;
    @TableField(exist = false)
    private Student student;




}
