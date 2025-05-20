package com.project.humanresource.service;

import com.project.humanresource.entity.User;
import com.project.humanresource.entity.UserRole;
import com.project.humanresource.repository.UserRepository;
import com.project.humanresource.repository.UserRoleRepository;
import com.project.humanresource.utility.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public Optional<UserRole> findByUserStatus(UserStatus userStatus) {
        return userRoleRepository.findByUserStatus(userStatus);
    }

    public List<UserRole> findAllRole(Long userId){
        return userRoleRepository.findByUserId(userId);
    }

    UserStatus resolveUserStatus(Long roleId) {
        if (roleId == null || roleId >= UserStatus.values().length) {
            return UserStatus.PENDING;
        }
        return UserStatus.values()[roleId.intValue()];
    }
}