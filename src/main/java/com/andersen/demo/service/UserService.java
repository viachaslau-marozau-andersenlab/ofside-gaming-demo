package com.andersen.demo.service;

import com.andersen.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findById(Long id);

    Page<User> findAll(Pageable pageable);
}
