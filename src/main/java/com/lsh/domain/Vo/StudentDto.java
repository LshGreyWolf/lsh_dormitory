package com.lsh.domain.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/4/5 11:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @NotBlank(message = "用户名不能为空！")
    private String stuNo;
    @NotBlank(message = "密码不能为空！")
    private String password;
    @NotBlank(message = "确认密码不能为空！")
    private String newPassword;
    @NotBlank(message = "手机号不能为空！")
    private String phone;
    @NotNull(message = "用户类型不能为空！")
    private Integer type;
}
