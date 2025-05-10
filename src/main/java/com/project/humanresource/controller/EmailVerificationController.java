package com.project.humanresource.controller;

import com.project.humanresource.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Bu sınıf bir REST API controller'dır
@RequestMapping("/api/auth") // Tüm endpointler bu URL ile başlar
@RequiredArgsConstructor // final alanları constructor ile otomatik enjekte eder
public class EmailVerificationController {

    private final EmailVerificationService verificationService;

    @PostMapping("/send-verification")
    public ResponseEntity<String> sendEmail(@RequestParam String email) {
        verificationService.sendVerificationEmail(email);
        return ResponseEntity.ok("Verification email sent.");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        boolean valid = verificationService.verifyToken(token);
        if (valid) {
            return ResponseEntity.ok("Email verified successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }

}
