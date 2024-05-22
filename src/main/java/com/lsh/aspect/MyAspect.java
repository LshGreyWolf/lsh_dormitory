package com.lsh.aspect;

import cn.hutool.json.JSONUtil;
import com.lsh.annotation.MyCustomAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("@annotation(com.lsh.annotation.MyCustomAnnotation)")
    public void myAnnotationPointcut() {}

    @Around("myAnnotationPointcut() && @annotation(annotation)")
    public Object doAround(ProceedingJoinPoint joinPoint, MyCustomAnnotation annotation) throws Throwable{
        String value = annotation.value();
        System.out.println("The value of MyCustomAnnotation is: " + value);
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getSignature();
        System.out.println("target = " + target);
        System.out.println("args = " + JSONUtil.toJsonStr(args));
        System.out.println("Before executing the method annotated with MyCustomAnnotation");
        Object result = joinPoint.proceed();
        System.out.println("After executing the method annotated with MyCustomAnnotation");

        return result;
    }
    @Before("myAnnotationPointcut() && @annotation(annotation)")
    public void doBefore(MyCustomAnnotation annotation) {
        String value = annotation.value();
        System.out.println("The value of MyCustomAnnotation is2322: " + value);
        System.out.println("Before executing the method annotated with MyCustomAnnotatio22n");
    }

    @AfterReturning(pointcut = "myAnnotationPointcut()", returning = "result")
    public void doAfterReturning(Object result) {
        System.out.println("After executing the method annotated with MyCustomAnnotation, returned result: " + result);
    }
}