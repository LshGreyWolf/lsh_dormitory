package com.lsh.domain;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (DormitoryStudent)表实体类
 *
 * @author lsh
 * @since 2023-03-28 17:28:09
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryStudent  {
    
    private Integer id;
    
    private Integer dormitoryId;
    
    private Integer bedId;
    
    private Integer studentId;
    
    private Date checkin;
    //0待入住/1已入住
    private Integer status;




}
