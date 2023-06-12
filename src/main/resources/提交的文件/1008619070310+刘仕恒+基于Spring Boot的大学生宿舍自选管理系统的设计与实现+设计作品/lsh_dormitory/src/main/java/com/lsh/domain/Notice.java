package com.lsh.domain;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Notice)表实体类
 *
 * @author lsh
 * @since 2023-03-28 17:21:46
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends Page {

    private Integer id;

    private String title;

    private String content;

    private Integer type;
    private Date createTime;

    private Integer userId;


    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<Integer> buildingIds;

    @TableField(exist = false)
    private Integer buildingId;


}
