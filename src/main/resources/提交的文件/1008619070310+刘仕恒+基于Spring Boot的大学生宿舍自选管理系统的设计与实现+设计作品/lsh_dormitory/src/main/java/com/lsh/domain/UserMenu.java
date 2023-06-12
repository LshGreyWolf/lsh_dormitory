package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (UserMenu)表实体类
 *
 * @author lsh
 * @since 2023-03-09 18:41:08
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMenu  {
    
    private Integer userId;
    
    private Integer menuId;




}
