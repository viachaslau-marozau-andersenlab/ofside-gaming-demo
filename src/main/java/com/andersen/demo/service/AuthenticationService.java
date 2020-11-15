package com.andersen.demo.service;

import com.andersen.demo.model.User;

public interface AuthenticationService {

    String AUTH_TOKEN_PREFIX = "Bearer ";
    String AUTH_HEADER_STRING = "Authorization";

    /**
     * Method to login and get auth token
     *
     * @param login login
     * @param login login
     * @return auth token
     */
    String login(String login, String password);

    /**
     * Parse and verify token and return logged user
     *
     * @param token token
     * @return logged user
     */
    User parseToken(String token);

    /**
     * Method to generate token based on user information
     *
     * @param user user @see User.class
     * @return auth token
     */
    String generateToken(User user);
}
