package com.lsh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/05/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    private Integer id;

    private String role;
}
