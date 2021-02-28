package com.tensquare.user.interceptor;

import entity.Result;
import entity.ResultEnum;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import system.Constants;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtIterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拦截器无论如何都放行，具体的判断还是到具体的业务中判断
        //拦截器只是对请求头中的token进行解析认证

        String authorization = request.getHeader("Authorization");
        if (!ObjectUtils.isEmpty(authorization)) {
            if (authorization.startsWith(Constants.AUTH_START)) {
                String token = authorization.substring(Constants.AUTH_START.length());
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String role = (String) claims.get(Constants.NAME_ROLE);
                    if(!ObjectUtils.isEmpty(role) && role.equals(Constants.ROLE_ADMIN)){
                        request.setAttribute(Constants.NAME_ROLE,role);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("token is bad!");
                }
            }
        }
        if (!authorization.startsWith(Constants.AUTH_START)) {
            return false;
        }


        return true;
    }

}
