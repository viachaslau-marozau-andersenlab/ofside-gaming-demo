package com.andersen.demo.repository;

import com.andersen.demo.model.UserRole;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    Optional<UserRole> findByRoleName(String roleName);
}
