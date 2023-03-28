package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (SelectionJoiner)表实体类
 *
 * @author lsh
 * @since 2023-03-28 17:25:14
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionJoiner  {
    
    private Integer id;
    
    private Integer selectionId;
    
    private Integer clazzId;




}
