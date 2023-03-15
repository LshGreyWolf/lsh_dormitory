package com.lsh.domain;

import lombok.Data;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-03-05 12:21:01
 */
@Data
public class User  {

    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String phone;
    /**
     * 0管理员/1宿管员
     */
    private Integer type;
    private String remark;


}

