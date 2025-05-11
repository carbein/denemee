package com.project.humanresource.repository;

import com.project.humanresource.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);

    @Query("SELECT u.userRole FROM User u WHERE u.id = :userId")
    List<UserRole> findByUserId(@Param("userId") Long userId);
} 