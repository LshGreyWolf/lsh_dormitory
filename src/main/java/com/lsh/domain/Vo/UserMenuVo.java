package com.lsh.domain.Vo;

import com.lsh.domain.Menu;
import com.lsh.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMenuVo {
    private User user;
    private Menu menu;
}
