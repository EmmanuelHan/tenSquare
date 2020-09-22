package com.tensquare.article.config.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *  资源服务访问配置
 *  2020年7月27日
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String secret;

	@Value("${security.oauth2.authorization.check-token-access}")
	private String checkTokenEndpointUrl;

	@Resource
	private DataSource dataSource;

	@Bean
	public TokenStore tokenStore(){
		return new JdbcTokenStore(dataSource);
	}

	@Bean
	public RemoteTokenServices tokenServices(){
		RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
		remoteTokenServices.setClientId(clientId);
		remoteTokenServices.setClientSecret(secret);
		remoteTokenServices.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
		return remoteTokenServices;
	}

	// 该资源服务器id必须在数据库记录中有配置，也就是对应token的用户必须该资源访问权限(密文：test_resource_secret)
	// 例如，我的数据库记录：
	// 'my_client_id','test_resource_id','$2a$10$I28j9B0T/roapkMEqfIHguARt0GgLyXwC/DOnFwPpXuQ0xTkrd632','user_info','authorization_code,refresh_token,implicit,password','http://localhost:7010/uaa/login','ROLE_ADMIN,ROLE_DEVICE,ROLE_VIDEO',3600,7200,'{\"systemInfo\":\"Atlas System\"}','true'
	// 通过授权模式或简化模式获取的token（对应用户为wx_takeout_client_id）具有访问资源服务器test_resource_id
	// 的权限，所以将该资源服务器id要与数据库的对应，否则无权访问
	// 注意：在不使用代码配置的情况下资源服务器id默认值为： oauth2-resource
	private static final String DEMO_RESOURCE_ID = "gate_way_server";

	/**
	 * @功能描述: 以代码形式配置资源服务器id，配置文件配置不生效
	 * @编写作者： lixx2048@163.com
	 * @开发日期： 2020年7月27日
	 * @历史版本： V1.0
	 * @参数说明：
	 * @返  回  值：
	 */
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenServices());
    }

	/**
	 * 注意：从网关经过的所有url都进行过滤，情况分为如下两种：
	 * 1、带access_token的参数url，过滤器会获取参数到授权中心去鉴权
	 * 2、不带access_token的url，过滤器会获取本地‘资源服务’鉴权配置--即如下方法(或注解形式配置)
	 * 注意“**”的使用, 使用不好可能导致权限控制失效！！！（如果url前面无单词如/oauth/...，但是匹配路径用** /oauth，就会导致权限控制失效)
	 */
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	// 其他匹配的[剩下的]任何请求都需要授权
//    	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
		http.authorizeRequests()//配置请求级别的安全细节
				//任何请求都必须认证后访问
			.anyRequest().authenticated()
			.and()
		    	.formLogin()
				//跨域请求
		    .and()
		    	.csrf().disable()
				// 进行http Basic认证
		    .httpBasic();
    }
}