package com.tensquare.user.service;

import com.alibaba.druid.util.StringUtils;
import com.tensquare.common.entity.Result;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component("unauthorizedEntryPoint")
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Map<String, String[]> paramMap = request.getParameterMap();
        StringBuilder param = new StringBuilder();
        paramMap.forEach((k, v) -> {
            param.append("&").append(k).append("=").append(v[0]);
        });
        param.deleteCharAt(0);
        String isRedirectValue = request.getParameter("isRedirect");
        if (!StringUtils.isEmpty(isRedirectValue) && Boolean.valueOf(isRedirectValue)) {
            response.sendRedirect("http://oauth.com/authPage/#/login?" + param.toString());
            return;
        }
        String authUrl = "http://oauth.com/auth/oauth/authorize?" + param.toString() + "&isRedirect=true";
        Result result = new Result();
        result.setCode(800);
        result.setData(authUrl);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.print(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}