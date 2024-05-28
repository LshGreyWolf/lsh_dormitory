package com.lsh.annotation;

import com.lsh.config.IpCheckConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
@ComponentScan("com.lsh.config.ip") // 这里是IpCheckConfig的包名
@Import(IpCheckConfig.class)
public @interface EnableIpCheck {
}
