package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 充值表(Account)表实体类
 *
 * @author lsh
 * @since 2023-04-04 19:03:34
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account  {
    
    private Integer id;
    //1-微信，2-支付宝，3-现金
    private Integer accountWay;
    //1-澡卡，2-水卡
    private Integer accountType;
    //余额
    private Integer balance;
    //卡号
    private Integer accountCard;
    //学生id
    private Integer studentId;
    //学号
    private Integer stuNo;
    
    private String remark;




}
