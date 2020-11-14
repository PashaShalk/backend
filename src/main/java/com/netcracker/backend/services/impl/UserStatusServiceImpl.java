package com.netcracker.backend.services.impl;

import com.netcracker.backend.model.UserStatus;
import com.netcracker.backend.model.UserStatusEnum;
import com.netcracker.backend.repositories.UserStatusRepository;
import com.netcracker.backend.services.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatusServiceImpl implements UserStatusService {

    private final UserStatusRepository userStatusRepository;

    @Autowired
    public UserStatusServiceImpl(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public UserStatus getByStatus(UserStatusEnum status) {
        return userStatusRepository.findByStatus(status);
    }
}
