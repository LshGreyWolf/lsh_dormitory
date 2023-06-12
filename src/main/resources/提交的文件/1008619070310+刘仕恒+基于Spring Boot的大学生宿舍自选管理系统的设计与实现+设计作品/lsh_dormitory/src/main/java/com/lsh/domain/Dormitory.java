package com.lsh.domain;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Dormitory)表实体类
 *
 * @author makejava
 * @since 2023-03-26 14:36:18
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dormitory {

    private Integer id;

    private String no;

    private Integer sex;

    private Integer capacity;

    private Integer storeyId;

    private Integer buildingId;

    @Version
    private Integer version;
    @TableField(exist = false)
    private Building building;
    @TableField(exist = false)
    private Storey storey;


}

