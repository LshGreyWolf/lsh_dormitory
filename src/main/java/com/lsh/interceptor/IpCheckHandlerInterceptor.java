package com.lsh.interceptor;

import com.lsh.annotation.IpCheck;
import com.lsh.utils.IpUtils;
import lombok.NonNull;
import lombok.var;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class IpCheckHandlerInterceptor implements HandlerInterceptor, EmbeddedValueResolverAware {
    private StringValueResolver stringValueResolver;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        // 检查是否有IpWhitelistCheck注解，并且是否开启IP白名单检查
        if (!(handler instanceof HandlerMethod)) {
            return true;  // 如果没有注解或者注解中关闭了IP白名单检查，则继续处理请求
        }
        var handlerMethod = (HandlerMethod) handler;
        var method = handlerMethod.getMethod();
        var annotation = AnnotationUtils.getAnnotation(method, IpCheck.class);
        if (annotation == null) {
            return true;
        }
        var clientIp = IpUtils.getIp();

        // 检查客户端IP是否在白名单中
        var whiteList = Stream.of(Optional.ofNullable(stringValueResolver.resolveStringValue(annotation.whiteList()))
                        .map(it -> it.split(","))
                        .orElse(new String[]{}))
                .filter(StringUtils::hasText)
                .map(String::trim)
                .collect(Collectors.toSet());
        if (!whiteList.isEmpty() && whiteList.contains(clientIp)) {
            return true; // IP在白名单中，继续处理请求
        }
        var blackList = Stream.of(Optional.ofNullable(stringValueResolver.resolveStringValue(annotation.blackList()))
                        .map(it -> it.split(","))
                        .orElse(new String[]{}))
                .filter(StringUtils::hasText)
                .map(String::trim)
                .collect(Collectors.toSet());
        if (!blackList.isEmpty() && !blackList.contains(clientIp)) {
            return true; // IP不在黑名单中，继续处理请求
        }
        // IP不在白名单中，可以返回错误响应或者抛出异常
        // 例如，返回一个 HTTP 403 错误
        throw new RuntimeException("Access denied, remote ip " + clientIp + " is not allowed.");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
