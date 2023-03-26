package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Storey)表实体类
 *
 * @author makejava
 * @since 2023-03-26 13:14:24
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storey {
    
    private Integer id;
    
    private String name;
    
    private Integer buildingId;
    
    private String remark;




}

