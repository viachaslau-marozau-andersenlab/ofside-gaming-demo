package com.andersen.demo.security;

import com.andersen.demo.model.User;
import com.andersen.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.andersen.demo.service.AuthenticationService.AUTH_HEADER_STRING;
import static com.andersen.demo.service.AuthenticationService.AUTH_TOKEN_PREFIX;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader(AUTH_HEADER_STRING);

        if (Objects.nonNull(authHeader) && authHeader.startsWith(AUTH_TOKEN_PREFIX)) {
            String authToken = authHeader.substring(AUTH_TOKEN_PREFIX.length());
            User user = authenticationService.parseToken(authToken);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}