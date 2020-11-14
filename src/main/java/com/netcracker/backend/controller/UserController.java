package com.netcracker.backend.controller;

import com.netcracker.backend.model.LoginData;
import com.netcracker.backend.model.User;
import com.netcracker.backend.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/authorization")
    public User authorizeUser(@RequestBody LoginData loginData) {
        System.out.println(loginData);
        return userService.authorize(loginData.getEmail(), loginData.getPassword());
    }

    @PostMapping("/registration")
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/id/{ID}")
    public User getUserByID(@PathVariable String ID) {
        return userService.getUserByID(ID);
    }

    @GetMapping("/nickname/{nickname}")
    public User getUserByNickname(@PathVariable String nickname) {
        return userService.getUserByNickname(nickname);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/page/{page}/count/{count}")
    public List<User> getAllUsers(@PathVariable Integer page, @PathVariable Integer count) {
        return userService.getAllUsers(page, count);
    }

    @GetMapping("/count")
    public Long getCountAllUsers() {
        return userService.getCountAllUsers();
    }

    @GetMapping("/blocking/{ID}")
    public void blockUser(@PathVariable Long ID) {
        userService.blockUser(ID);
    }

    @GetMapping("/unblocking/{ID}")
    public void unblockUser(@PathVariable Long ID) {
        userService.unblockUser(ID);
    }

    @PostMapping("/updating")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
