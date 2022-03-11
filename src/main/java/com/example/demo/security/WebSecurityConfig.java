package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// @PreAuthorize 어노테이션을 메소드 단위로 추가하기 위해서 사용
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;

    public WebSecurityConfig(
            TokenProvider tokenProvider
    ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()

                .exceptionHandling()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                // swagger 401 issue
                .antMatchers("/").anonymous()
                .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/v2/**").anonymous()
                .antMatchers("/csrf").anonymous()

                // before login 401 issue
                .antMatchers("/api/v1/account/login").permitAll()   // login
                .antMatchers("/api/v1/account").permitAll() // sign
                .antMatchers("/api/v1/account/email/**").permitAll()    // send email
                .antMatchers("/api/v1/account/password/find").permitAll()   // pw find
                .antMatchers("/api/v1/account/password/change").permitAll() // pw chg

                // auth
                .antMatchers("/api/v1/authenticate").permitAll() // pw chg

                // mail test
                .antMatchers("/api/v1/mail/send").permitAll()

                // others
                .anyRequest().permitAll()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}
