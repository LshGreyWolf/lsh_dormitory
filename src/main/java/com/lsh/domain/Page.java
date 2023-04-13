package com.lsh.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @author: Administrator
 * @date: 2023/03/05
 * @copyright 版权信息
 */
@Data
public class Page {
    @TableField(exist = false)
    private Integer page = 1;
    @TableField(exist = false)
    private Integer limit = 10;

}
