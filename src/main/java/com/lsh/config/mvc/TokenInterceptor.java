package com.lsh.config.mvc;


import com.lsh.config.exception.MyException;
import com.lsh.enums.AppHttpCodeEnum;
import com.lsh.utils.JWTUtil;
import com.lsh.domain.Student;
import com.lsh.domain.User;
import com.lsh.utils.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JWTUtil.token);

        String type = JWTUtil.getType(token);
        if("USER".equals(type)){
            //根据token获取user对象
            User user = JWTUtil.getUser(token);
            if(user == null){
                throw  new MyException(AppHttpCodeEnum.TIMEOUT_OR_ILLEGAL_TOKEN);
            }
            String newToken = JWTUtil.sign(user);
            UserHolder.saveUser(user);
            response.setHeader(JWTUtil.token,newToken);
            response.setHeader("Access-Control-Expose-Headers", JWTUtil.token);
            request.setAttribute("user",user);

        }else if("STUDENT".equals(type)){
            //根据token获取user对象
            Student student = JWTUtil.getStudent(token);

            if(student == null){
                throw  new MyException(AppHttpCodeEnum.TIMEOUT_OR_ILLEGAL_TOKEN);
            }

            String newToken = JWTUtil.signForStudent(student);
            UserHolder.saveStudent(student);
            response.setHeader(JWTUtil.token,newToken);
            response.setHeader("Access-Control-Expose-Headers", JWTUtil.token);
            request.setAttribute("student",student);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
