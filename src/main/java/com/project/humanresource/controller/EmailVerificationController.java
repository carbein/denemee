package com.project.humanresource.controller;

import com.project.humanresource.entity.EmailVerification;
import com.project.humanresource.entity.Employee;
import com.project.humanresource.repository.EmailVerificationRepository;
import com.project.humanresource.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController // Bu sınıf bir REST API controller'dır
@RequestMapping("/api/auth") // Tüm endpointler bu URL ile başlar
@RequiredArgsConstructor // final alanları constructor ile otomatik enjekte eder
public class EmailVerificationController {

    private final EmailVerificationRepository repository; // Kodları sorgulamak için
    private final EmployeeRepository employeeRepository; // Çalışanı güncellemek için

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("employeeId") Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmailVerification verification = repository.findByEmployee(employee);
        if (verification == null || verification.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Verification code expired or missing");
        }

        employee.setEmailVerified(true);
        employeeRepository.save(employee);
        repository.delete(verification); // token'ı siliyoruz

        return ResponseEntity.ok("Your email has been verified.");
    }
}
