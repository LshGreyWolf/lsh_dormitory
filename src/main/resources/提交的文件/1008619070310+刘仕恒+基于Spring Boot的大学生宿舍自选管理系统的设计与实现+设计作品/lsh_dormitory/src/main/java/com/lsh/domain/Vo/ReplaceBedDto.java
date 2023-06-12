package com.lsh.domain.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/4/15 8:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplaceBedDto {

    Integer clazzId;

    Integer collegeId;

    Integer gradeId;

    Integer majorId;

    Integer studentId;

    Integer bedId;
}
