package com.netcracker.backend.repositories;

import com.netcracker.backend.model.UserRole;
import com.netcracker.backend.model.UserRoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    UserRole findByRole(UserRoleEnum role);
}
