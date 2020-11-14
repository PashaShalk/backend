package com.netcracker.backend.services;

import com.netcracker.backend.model.UserStatus;
import com.netcracker.backend.model.UserStatusEnum;

public interface UserStatusService {
    UserStatus getByStatus(UserStatusEnum status);
}
