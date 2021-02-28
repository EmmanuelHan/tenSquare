package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import system.Constants;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class WebFilter extends ZuulFilter {
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
        log.info("通过Zuul-Web过滤器");
        //获取头信息
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String authorization = request.getHeader(Constants.HEAD_AUTH);
        if(!ObjectUtils.isEmpty(authorization)){
            currentContext.addZuulRequestHeader(Constants.HEAD_AUTH,authorization);
        }
        return null;
    }
}
