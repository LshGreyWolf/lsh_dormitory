package com.lsh.domain;

import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/05/22
 */
@Data

public class LogEntity {
    private String message;
    private Long Id;
    private Integer isAccumulation;
    private Long receiveUserId;
}
