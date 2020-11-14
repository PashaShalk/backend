package com.netcracker.backend.services;

import com.netcracker.backend.model.User;

import java.util.List;

public interface UserService {
    User authorize(String email, String password);

    User register(User user);

    User getUserByID(String ID);

    User getUserByNickname(String nickname);

    List<User> getAllUsers(Integer page, Integer count);

    Long getCountAllUsers();

    void blockUser(Long ID);

    void unblockUser(Long ID);

    User updateUser(User user);

    User getUserByEmail(String email);
}
