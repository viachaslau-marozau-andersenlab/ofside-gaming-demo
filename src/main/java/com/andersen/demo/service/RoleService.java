package com.andersen.demo.service;

import com.andersen.demo.model.UserRole;

public interface RoleService {

    UserRole findByRoleName(String role);
}
