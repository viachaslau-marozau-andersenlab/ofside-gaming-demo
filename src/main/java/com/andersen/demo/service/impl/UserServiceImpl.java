package com.andersen.demo.service.impl;

import com.andersen.demo.model.User;
import com.andersen.demo.repository.UserRepository;
import com.andersen.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with id %s not found", id)));
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
