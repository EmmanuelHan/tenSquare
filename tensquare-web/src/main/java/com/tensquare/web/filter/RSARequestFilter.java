package com.tensquare.web.filter;

import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.tensquare.web.rsa.RsaKeys;
import com.tensquare.web.service.RsaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class RSARequestFilter extends ZuulFilter {

    @Resource
    private RsaService rsaService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        /*
         * 1. 从request body中读取出加密后的请求参数
         * 2. 将加密后的请求参数用私钥解密
         * 3. 将解密后的请求参数写回request body中
         * 4. 转发请求
         */
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            String decryptData = null;

            String url = request.getRequestURL().toString();
            InputStream stream = ctx.getRequest().getInputStream();
            String requestParam = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);

            if (!Strings.isNullOrEmpty(requestParam)) {
                log.info("请求体中的密文: {}", requestParam);
                decryptData = rsaService.RSADecryptDataPEM(requestParam, RsaKeys.getServerPrvKeyPkcs8());
                log.info("解密后的内容: {}", decryptData);
            }

            log.info("request: {} >>> {}, data={}", request.getMethod(), url, decryptData);
            if (!Strings.isNullOrEmpty(decryptData)) {
                log.info("json字符串写入request body");
                final byte[] reqBodyBytes = decryptData.getBytes();
                ctx.setRequest(new HttpServletRequestWrapper(request) {
                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ServletInputStreamWrapper(reqBodyBytes);
                    }

                    @Override
                    public int getContentLength() {
                        return reqBodyBytes.length;
                    }

                    @Override
                    public long getContentLengthLong() {
                        return reqBodyBytes.length;
                    }
                });
            }

            log.info("转发request");
            // 设置request请求头中的Content-Type为application/json，否则api接口模块需要进行url转码操作
            ctx.addZuulRequestHeader("Content-Type", String.valueOf(MediaType.APPLICATION_JSON) + ";charset=UTF-8");
        } catch (Exception e) {
            log.info("{} 运行出错",this.getClass().getName(), e);
        }
        return null;
    }
}
