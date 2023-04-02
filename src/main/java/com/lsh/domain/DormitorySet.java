package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (DormitorySet)表实体类
 *
 * @author lsh
 * @since 2023-03-28 17:27:15
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormitorySet  extends Page{
    
    private Integer id;
    
    private String prefix;
    
    private Integer start;
    
    private Integer end;
    
    private Integer buildingId;
    
    private Integer storeyId;
    
    private Integer capacity;




}
