package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import system.Constants;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拦截器无论如何都放行，具体的判断还是到具体的业务中判断
        //拦截器只是对请求头中的token进行解析认证

        final String authorization = request.getHeader(Constants.HEAD_AUTH);
        if (!ObjectUtils.isEmpty(authorization) && authorization.startsWith(Constants.AUTH_START)) {
            String token = authorization.substring(Constants.AUTH_START.length());
            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (!ObjectUtils.isEmpty(claims)) {
                    String role = (String) claims.get(Constants.NAME_ROLE);
                    if (!ObjectUtils.isEmpty(role) && Constants.ROLE_ADMIN.equals(role)) {
                        request.setAttribute(Constants.ADMIN_CLAIMS, claims);
                    }
                    if (!ObjectUtils.isEmpty(role) && Constants.ROLE_USER.equals(role)) {
                        request.setAttribute(Constants.USER_CLAIMS, claims);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("token is bad!");
            }
        }
        if (!ObjectUtils.isEmpty(authorization)) {
            return authorization.startsWith(Constants.AUTH_START);
        }
        return true;
    }

}
