//package com.tensquare.user.config.auth;
//
//import com.tensquare.user.service.IUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//@Configuration
//@EnableAuthorizationServer//开启认证服务中心
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private DataSource dataSource;
//
//    @Resource
//    private IUserService userService;
//
//    @Resource
//    private ClientDetailsService clientDetailsService;
//
//    @Resource
//    private BCryptPasswordEncoder encoder;
//
//    @Primary
//    @Bean
//    public TokenStore tokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Bean
//    public ClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//
//    @Bean
//    protected AuthorizationCodeServices authorizationCodeServices() {
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }
//
//    /**
//     * 授权信息持久化实现
//     * @return
//     */
//    @Bean
//    public ApprovalStore approvalStore(){
//        return new JdbcApprovalStore(dataSource);
//    }
//
//    /**
//     * 定义令牌端点上的安全约束。
//     * @param security
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.passwordEncoder(encoder);
//        //允许checkToken访问
////        security.tokenKeyAccess("permitAll()");
//        security.tokenKeyAccess("isAuthenticated()")
//            .checkTokenAccess("isAuthenticated()");
//        //允许客户端访问Oauth2授权接口
//        security.allowFormAuthenticationForClients();
//
//    }
//
//    /**
//     * 定义客户端详细信息服务的配置程序。可以初始化客户端详细信息，或者您可以仅引用现有存储。
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(dataSource);
////        clients.withClientDetails(clientDetailsService());
//    }
//
//    /**
//     * 定义授权和令牌端点以及令牌服务。
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authorizationCodeServices(authorizationCodeServices())
//                // 使用的认证管理器-默认包含登录认证、用户名密码认证
//                .authenticationManager(authenticationManager)
//                //认证信息持久化实现
//                .approvalStore(approvalStore())
//                // token存储在数据库中-生产环境使用以免服务器崩溃
//                .tokenStore(tokenStore())
//                // 设置refresh token是否重复使用，若无，refresh_token会有UserDetailsService is required错误
//                .reuseRefreshTokens(false)
//                // 从数据查用户授权信息
//                .userDetailsService(userService)
//                // 授权请求提交方式
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
//
//        // 配置tokenServices参数
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore());// token存储在redis中-如果存储在jdbc中就需要建立token存储表
//        tokenServices.setSupportRefreshToken(true);// 支持更换token
//        tokenServices.setClientDetailsService(clientDetailsService);// jdbc具体的秘钥认证服务-如果存储在jdbc中就需要建立oauth_client_details表
//        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(30));// token有效期自定义设置，30分钟
//        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1));	// 1天之内可以刷新token
//
//        endpoints.tokenServices(tokenServices);
//    }
//
//
//
//}
