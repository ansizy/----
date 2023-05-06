package com.example.demo.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MECHREVO
 */

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("token");

        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        //执行认证
        if(StrUtil.isBlank(token)){
            return false;
        }

        //获取token 中的userId
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j){
            return false;
        }

        //根据token中的userId查询数据
        User user = userService.getById(userId);
        if(user == null){
            return false;
        }
        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword())).build();
        try{
            //验证token
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e){
            return false;
        }

        return true;
    }
}
