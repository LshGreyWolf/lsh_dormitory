package com.lsh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-03-05 12:21:01
 */
@Data
public class User extends Page {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;
    @TableField(exist = false)
    private String newPassword;

    private String name;

    private String phone;
    /**
     * 0- 管理员 /1 -宿管员
     */
    private Integer type;
    private String remark;
    private String sex;
    private Integer status;
    @TableField(exist = false)
    private List<Integer> menuIds;

    private String file;


}

