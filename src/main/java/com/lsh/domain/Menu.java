package com.lsh.domain;


import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Menu)表实体类
 *
 * @author lsh
 * @since 2023-03-09 18:40:58
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private Integer id;

    private String title;

    private String icon;

    private String href;

    private String target;

    private Integer parentId;
    //0:管理员/宿管员;1:学生
    private Integer type;
    @TableField(exist = false)
    private List<Menu> child;


}
