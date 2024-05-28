package com.lsh.config;

import com.lsh.interceptor.IpCheckHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class IpCheckConfig implements WebMvcConfigurer {

    @Resource
    private IpCheckHandlerInterceptor ipCheckHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipCheckHandlerInterceptor);
    }

}
