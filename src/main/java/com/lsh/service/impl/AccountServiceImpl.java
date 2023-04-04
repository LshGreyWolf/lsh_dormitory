package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Account;
import com.lsh.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.AccountService;

/**
 * 充值表(Account)表服务实现类
 *
 * @author lsh
 * @since 2023-04-04 19:03:34
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
