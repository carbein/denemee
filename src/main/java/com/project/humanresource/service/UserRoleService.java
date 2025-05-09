package com.project.humanresource.service;

import com.project.humanresource.entity.UserRole;
import com.project.humanresource.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public Optional<UserRole> findByName(String name) {
        return userRoleRepository.findByName(name);
    }
} 