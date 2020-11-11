package com.andersen.demo.service;

import com.andersen.demo.model.User;

public interface AuthenticationService {

    String AUTH_TOKEN_PREFIX = "Bearer ";
    String AUTH_HEADER_STRING = "Authorization";

    String login(String login, String password);

    User parseToken(String token);

    String generateToken(User user);
}
