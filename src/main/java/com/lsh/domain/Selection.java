package com.lsh.domain;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Selection)表实体类
 *
 * @author lsh
 * @since 2023-03-28 17:22:50
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Selection extends Page {

    private Integer id;

    private String name;

    private Date startTime;

    private Date endTime;

    private String remark;
    @TableField(exist = false)
    private List<Integer> clazzIds;
    @TableField(exist = false)
    private List<Org> clazzes;


}
