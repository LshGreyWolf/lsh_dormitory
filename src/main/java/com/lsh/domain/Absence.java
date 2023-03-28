package com.lsh.domain;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Absence)表实体类
 *
 * @author lsh
 * @since 2023-03-28 17:26:15
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Absence  {
    
    private Integer id;
    
    private Integer studentId;
    
    private Integer dormitoryId;
    
    private Date startTime;
    
    private Date endTime;
    
    private String remark;




}
