package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (SelectionDormitory)表实体类
 *
 * @author lsh
 * @since 2023-03-28 17:24:13
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionDormitory  {
    
    private Integer id;
    
    private Integer dormitoryId;
    
    private Integer clazzId;




}
