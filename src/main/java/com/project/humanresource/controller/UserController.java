package com.project.humanresource.controller;

import com.project.humanresource.entity.User;
import com.project.humanresource.service.UserService;
import com.project.humanresource.utility.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<User>> createUser(@RequestBody User user) {
        User saved = userService.save(user);
        BaseResponse<User> response = new BaseResponse<>(true, "User created", saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-email")
    public ResponseEntity<BaseResponse<User>> getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.ok(new BaseResponse<>(false, "User not found", null));
        }
        return ResponseEntity.ok(new BaseResponse<>(true, "User found", user));
    }
} 