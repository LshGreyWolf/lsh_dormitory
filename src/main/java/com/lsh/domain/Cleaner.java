package com.lsh.domain;


import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Cleaner)表实体类
 *
 * @author makejava
 * @since 2023-04-15 10:04:44
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cleaner  extends Page{
    
    private Integer id;
    
    private String name;
    
    private Integer buildingId;

    @TableField(exist = false)
    private Building building;

    private  Integer phone;

    @TableField(exist = false)
    private List<Integer> buildingIds;




}

