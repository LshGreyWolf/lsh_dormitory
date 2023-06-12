package com.lsh.domain.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.util.Password;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/4/14 21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {

    String password;

    String newPassword;
    String reNewPassword;
    /***
    *0为 user   1 为学生
    */
    Integer type;
}
