package com.project.humanresource.controller;

import com.project.humanresource.entity.UserRole;
import com.project.humanresource.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public ResponseEntity<UserRole> createRole(@RequestBody UserRole userRole) {
        return ResponseEntity.ok(userRoleService.save(userRole));
    }

    @GetMapping("/by-name")
    public ResponseEntity<UserRole> getRoleByName(@RequestParam String name) {
        Optional<UserRole> role = userRoleService.findByName(name);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
} 