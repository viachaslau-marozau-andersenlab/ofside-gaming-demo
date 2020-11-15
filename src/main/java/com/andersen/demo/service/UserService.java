package com.andersen.demo.service;

import com.andersen.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    /**
     * Method to get user by id
     *
     * @param id user id
     * @return user
     */
    User findById(Long id);

    /**
     * Method to get all users
     *
     * @param pageable page settings
     * @return user page
     */
    Page<User> findAll(Pageable pageable);
}
