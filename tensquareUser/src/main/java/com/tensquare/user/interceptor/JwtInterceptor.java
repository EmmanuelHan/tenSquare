package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String authorization = request.getHeader("Authorization");
//        if(!ObjectUtils.isEmpty(authorization)){
//            try {
//                Claims claims = jwtUtil.parseJWT(authorization);
//                if(!ObjectUtils.isEmpty(claims)){
//                    String id = claims.getId();
//                    String subject = claims.getSubject();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        }

        return true;
    }

}
