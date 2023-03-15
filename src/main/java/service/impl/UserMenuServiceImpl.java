package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.UserMenu;
import com.lsh.mapper.UserMenuMapper;
import org.springframework.stereotype.Service;
import com.lsh.service.UserMenuService;

/**
 * (UserMenu)表服务实现类
 *
 * @author lsh
 * @since 2023-03-09 18:41:09
 */
@Service("userMenuService")
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenu> implements UserMenuService {

}
