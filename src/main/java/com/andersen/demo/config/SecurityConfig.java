package com.andersen.demo.config;

import com.andersen.demo.security.JwtTokenAuthenticationEntryPoint;
import com.andersen.demo.security.TokenAuthenticationFilter;
import com.andersen.demo.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final AuthenticationService authenticationService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .disable()
                .cors();
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeRequests()
                .antMatchers("/v1/metrics").authenticated()
                .antMatchers("/v1/metrics").authenticated()
                .antMatchers("/v1/metrics").authenticated()
                .anyRequest().permitAll();

        httpSecurity.exceptionHandling().authenticationEntryPoint(new JwtTokenAuthenticationEntryPoint(handlerExceptionResolver));
        httpSecurity.addFilterBefore(new TokenAuthenticationFilter(authenticationService), UsernamePasswordAuthenticationFilter.class);
    }
}