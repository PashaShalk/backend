package com.netcracker.backend.services.impl;

import com.netcracker.backend.model.UserRole;
import com.netcracker.backend.model.UserRoleEnum;
import com.netcracker.backend.repositories.UserRoleRepository;
import com.netcracker.backend.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole getByRole(UserRoleEnum role) {
        return userRoleRepository.findByRole(role);
    }
}
