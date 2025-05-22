package com.project.humanresource.controller;

import com.project.humanresource.dto.response.BaseResponse;
import com.project.humanresource.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Bu sınıf bir REST API controller'dır
@RequestMapping("/api/auth") // Tüm endpointler bu URL ile başlar
@RequiredArgsConstructor // final alanları constructor ile otomatik enjekte eder
public class EmailVerificationController {


    private final EmailVerificationService emailVerificationService;

    @PostMapping("/send-verification")
    public ResponseEntity<String> sendEmail(@RequestParam String email) {
        emailVerificationService.sendVerificationEmail(email);
        return ResponseEntity.ok("Verification email sent.");
    }

    @GetMapping("/verify-email")
    public ResponseEntity<BaseResponse<String>> verifyEmail(@RequestParam String token) {
            emailVerificationService.verifyToken(token);

            return ResponseEntity.ok(BaseResponse.<String>builder()
                            .code(200)
                            .message("Doğrulama işlemi başarılı ")
                            .data("Email başarıyla doğrulandı.")
                    .build());

//        boolean valid = verificationService.verifyToken(token);
//        if (valid) {
//            return ResponseEntity.ok("Email verified successfully!");
//        } else {
//            return ResponseEntity.badRequest().body("Invalid or expired token.");
//        }
    }

}
