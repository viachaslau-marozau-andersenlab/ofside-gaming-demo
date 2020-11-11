package com.andersen.demo.service.impl;

import com.andersen.demo.model.UserRole;
import com.andersen.demo.repository.UserRoleRepository;
import com.andersen.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Primary
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UserRoleRepository repository;

    @Override
    public UserRole findByRoleName(String roleName) {
        return repository.findByRoleName(roleName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Role with name %s not found", roleName)));
    }
}
