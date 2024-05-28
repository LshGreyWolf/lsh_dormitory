package com.lsh;

import com.lsh.annotation.EnableIpCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableAsync
@EnableIpCheck
public class DormitoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitoryApplication.class, args);
        log.info("项目启动成功");
    }



}
