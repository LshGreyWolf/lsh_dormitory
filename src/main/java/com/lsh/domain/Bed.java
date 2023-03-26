package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Bed)表实体类
 *
 * @author makejava
 * @since 2023-03-26 15:18:56
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bed  {
    
    private Integer id;
    
    private String bno;
    
    private Integer dormitoryId;




}

