package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import system.Constants;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ManagerFilter extends ZuulFilter {

    @Resource
    private JwtUtil jwtUtil;

    /**
     * 请求前(pre) 或者 请求后(post)执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器的执行顺序，数字越小，越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 该过滤器的启用，true 开启，false 关闭
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作 return任何Object的值都表示继续执行
     * setSendZuulResponse(false)不在继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info("通过Zuul过滤器");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //域请求直接通过
        if("OPTIONS".equals(request.getMethod())){
            return null;
        }
        //登录请求通过
        if(request.getRequestURL().indexOf("/admin/login") > 0){
            return null;
        }

        String authorization = request.getHeader(Constants.HEAD_AUTH);
        if(!ObjectUtils.isEmpty(authorization)){
            try {
                Claims claims = jwtUtil.parseJWT(authorization);
                String roles = (String) claims.get(Constants.NAME_ROLE);
                if(Constants.ROLE_ADMIN.equals(roles)){
                    //把头信息转发下去，并继续运行
                    currentContext.addZuulRequestHeader(Constants.HEAD_AUTH,authorization);
                    return null;
                }
            } catch (Exception e) {
                log.error("token解析异常",e);
                currentContext.setSendZuulResponse(false);//终止运行
            }
         }
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
