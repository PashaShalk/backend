package com.netcracker.backend.services;

import com.netcracker.backend.model.UserRole;
import com.netcracker.backend.model.UserRoleEnum;

public interface UserRoleService {
    UserRole getByRole(UserRoleEnum role);
}
