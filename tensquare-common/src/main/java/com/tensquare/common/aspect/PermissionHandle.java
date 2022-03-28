package com.tensquare.common.aspect;

import com.tensquare.common.annotation.Permission;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import com.tensquare.common.system.Constants;
import com.tensquare.common.util.JwtUtil;
import com.tensquare.common.util.StringUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author EmmanuelHan
 */
@Slf4j
@Aspect
@Configuration
public class PermissionHandle {

    @Resource
    private JwtUtil jwtUtil;

//    @Pointcut("@annotation(com.tensquare.common.annotation.Permission)")
//    public void role() {
//    }

    @Around("@annotation(com.tensquare.common.annotation.Permission)")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
        //拦截器无论如何都放行，具体的判断还是到具体的业务中判断
        //拦截器只是对请求头中的token进行解析认证
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Permission annotation = method.getAnnotation(Permission.class);
        String[] roles = annotation.value();
        log.debug("该方法需要的权限为：{}", Arrays.toString(roles));

        List<String> roleList = Arrays.asList(roles);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        final String authorization = request.getHeader(Constants.HEAD_AUTH);
        if (!ObjectUtils.isEmpty(authorization) && authorization.startsWith(Constants.AUTH_START)) {
            String token = authorization.substring(Constants.AUTH_START.length());
            try {
                Claims claims = jwtUtil.parseJwt(token);
                if(!ObjectUtils.isEmpty(claims)){
                    String roleName = (String) claims.get(Constants.NAME_ROLE);
                    if (!ObjectUtils.isEmpty(roleName) && Constants.ROLE_ADMIN.equals(roleName)) {
                        request.setAttribute(Constants.ADMIN_CLAIMS, claims);
                        return point.proceed();
                    }
                    if (!ObjectUtils.isEmpty(roleName) && Constants.ROLE_USER.equals(roleName)) {
                        request.setAttribute(Constants.USER_CLAIMS, claims);
                        return point.proceed();
                    }
                    if(roleList.contains(roleName)){
                        return point.proceed();
                    }
                    return new Result(ResultEnum.NO_ACCESS);
                }
            } catch (Exception e) {
                log.info(StringUtil.getException(e));
            }
        }
        return new Result(ResultEnum.NO_ACCESS);
    }
}
