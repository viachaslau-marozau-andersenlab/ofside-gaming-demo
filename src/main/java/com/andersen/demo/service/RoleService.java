package com.andersen.demo.service;

import com.andersen.demo.model.UserRole;

public interface RoleService {

    /**
     * Method to get role by role name
     *
     * @param role role name
     * @return role
     */
    UserRole findByRoleName(String role);
}
