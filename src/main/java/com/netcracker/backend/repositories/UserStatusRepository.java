package com.netcracker.backend.repositories;

import com.netcracker.backend.model.UserStatus;
import com.netcracker.backend.model.UserStatusEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends CrudRepository<UserStatus, Long> {
    UserStatus findByStatus(UserStatusEnum status);
}
