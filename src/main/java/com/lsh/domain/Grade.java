package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Grade)表实体类
 *
 * @author makejava
 * @since 2023-03-18 13:00:25
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade extends Page  {
    
    private Integer id;
    
    private String name;




}

