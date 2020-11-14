package com.netcracker.backend.services.impl;

import com.netcracker.backend.model.User;
import com.netcracker.backend.model.UserRoleEnum;
import com.netcracker.backend.model.UserStatusEnum;
import com.netcracker.backend.repositories.UserRepository;
import com.netcracker.backend.repositories.UserRoleRepository;
import com.netcracker.backend.repositories.UserStatusRepository;
import com.netcracker.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserStatusRepository userStatusRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, UserStatusRepository userStatusRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public User authorize(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return user.orElse(null);
    }

    @Override
    public User register(User user) {
        if (!userRepository.findByEmail(user.getEmail()).isPresent() &&
                !userRepository.findByNickname(user.getNickname()).isPresent()) {
            user.setRole(userRoleRepository.findByRole(UserRoleEnum.USER));
            user.setStatus(userStatusRepository.findByStatus(UserStatusEnum.ACTIVE));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User getUserByID(String ID) {
        return userRepository.findByID(Long.parseLong(ID)).isPresent()
                ? userRepository.findByID(Long.parseLong(ID)).get()
                : null;
    }

    @Override
    public User getUserByNickname(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            User user = userRepository.findByNickname(nickname).get();
            if (!user.getRole().getRole().equals(UserRoleEnum.ADMIN)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
//            if (!user.getRole().getRole().equals(UserRoleEnum.ADMIN)) {
                return user;
//            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers(Integer page, Integer count) {
        return userRepository.findByRole(userRoleRepository.findByRole(UserRoleEnum.USER),
                PageRequest.of(page, count));
    }

    @Override
    public Long getCountAllUsers() {
        return userRepository.countUsersByRole(userRoleRepository.findByRole(UserRoleEnum.USER));
    }

    @Override
    public void blockUser(Long ID) {
        Optional<User> users = userRepository.findByID(ID);
        if (users.isPresent()) {
            User user = users.get();
            user.setStatus(userStatusRepository.findByStatus(UserStatusEnum.BANNED));
            userRepository.save(user);
        }
    }

    @Override
    public void unblockUser(Long ID) {
        Optional<User> users = userRepository.findByID(ID);
        if (users.isPresent()) {
            User user = users.get();
            user.setStatus(userStatusRepository.findByStatus(UserStatusEnum.ACTIVE));
            userRepository.save(user);
        }
    }

    @Override
    public User updateUser(User oldUser) {
        Optional<User> users = userRepository.findByID(oldUser.getID());
        if (users.isPresent()) {
            User user = users.get();
            user.setFirstName(oldUser.getFirstName());
            user.setLastName(oldUser.getLastName());
            user.setProfileDescription(oldUser.getProfileDescription());
            return userRepository.save(user);
        }
        return null;
    }
}
