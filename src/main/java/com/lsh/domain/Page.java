package com.lsh.domain;

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
    private Integer page = 1;
    private Integer limit = 10;

}
