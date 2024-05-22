package com.lsh;

import com.lsh.domain.User;
import com.lsh.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/04/10
 */
@SpringBootTest
public class TestClass {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = User.builder().name("lsh").type(25).build();
        userService.registerUser(user);
    }
}
