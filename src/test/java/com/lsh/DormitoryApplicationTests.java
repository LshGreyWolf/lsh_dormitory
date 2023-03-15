package com.lsh;

import com.lsh.domain.User;
import com.lsh.mapper.UserMapper;
import com.lsh.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class DormitoryApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        List<String> names = users.stream().map(user -> user.getName()).collect(Collectors.toList());
        System.out.println("names = " + names);
    }

}
