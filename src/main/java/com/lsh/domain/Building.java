package com.lsh.domain;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 楼宇(Building)表实体类
 *
 * @author lsh
 * @since 2023-03-24 18:04:11
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building  extends Page{
    
    private Integer id;
    
    private String name;

    /**
     * 4人间
     * 6人间
     * 8人间
     */
    private Integer type;
    
    private Integer storeyNum;
    
    private Integer sex;
    
    private String remark;
    
    private Integer userId;
    @TableField(exist = false)
    private User user;




}
