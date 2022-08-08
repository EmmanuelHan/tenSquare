package com.tensquare.article.config.auth;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.logout().logoutSuccessUrl("http://localhost:8080/logout");
            http.authorizeRequests().anyRequest().authenticated();
            http.csrf().disable();
    }



}
