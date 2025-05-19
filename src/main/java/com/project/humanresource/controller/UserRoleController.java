package com.project.humanresource.controller;

import com.project.humanresource.entity.User;
import com.project.humanresource.entity.UserRole;
import com.project.humanresource.service.UserRoleService;
import com.project.humanresource.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;
    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<BaseResponse<UserRole>> createRole(@RequestBody UserRole userRole) {
//        UserRole saved = userRoleService.save(userRole);
//        BaseResponse<UserRole> response = new BaseResponse<>(true, "Role created", saved);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/by-email")
//    public ResponseEntity<BaseResponse<User>> getUserByEmail(@RequestParam String email) {
//        User user = userService.findByEmail(email).orElse(null);
//        if (user == null) {
//            return ResponseEntity.ok(new BaseResponse<>(false, "User not found", null));
//        }
//        return ResponseEntity.ok(new BaseResponse<>(true, "User found", user));
//    }
} 