package com.project.humanresource.controller;

import com.project.humanresource.entity.UserRole;
import com.project.humanresource.service.UserRoleService;
import com.project.humanresource.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<UserRole>> createRole(@RequestBody UserRole userRole) {
        UserRole saved = userRoleService.save(userRole);
        BaseResponse<UserRole> response = new BaseResponse<>(true, "Role created", saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-name")
    public ResponseEntity<BaseResponse<UserRole>> getRoleByName(@RequestParam String name) {
        UserRole role = userRoleService.findByName(name).orElse(null);
        if (role == null) {
            return ResponseEntity.ok(new BaseResponse<>(false, "Role not found", null));
        }
        return ResponseEntity.ok(new BaseResponse<>(true, "Role found", role));
    }
} 