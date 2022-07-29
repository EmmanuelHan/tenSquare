package com.tensquare.user.service;

import com.tensquare.common.entity.Result;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component("failureAuthentication")
public class FailureAuthentication extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        Result result = new Result();
        result.setCode(1000);
        result.setMessage("登录失败");
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}