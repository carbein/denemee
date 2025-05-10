package com.project.humanresource.repository;

import com.project.humanresource.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    EmailVerification findByCode(String code);
}
