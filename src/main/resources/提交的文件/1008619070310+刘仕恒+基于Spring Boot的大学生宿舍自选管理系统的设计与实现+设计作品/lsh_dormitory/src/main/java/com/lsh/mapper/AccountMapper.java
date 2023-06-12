package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Account;
import org.apache.ibatis.annotations.Mapper;


/**
 * 充值表(Account)表数据库访问层
 *
 * @author lsh
 * @since 2023-04-04 19:03:34
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
