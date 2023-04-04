package com.lsh.controller;


import com.lsh.domain.Account;
import com.lsh.utils.Result;
import org.springframework.web.bind.annotation.*;
import com.lsh.service.AccountService;

import javax.annotation.Resource;

/**
 * 充值表(Account)表控制层
 *
 * @author lsh
 * @since 2023-04-04 19:03:34
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/insertAccount")
    public Result insertAccount(@RequestBody Account account) {
        accountService.save(account);
        return Result.ok("成功");
    }

}

