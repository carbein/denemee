package com.project.humanresource.service;

// Email kodu veritabanına kaydetmek için gerekli entity ve repository
import com.project.humanresource.entity.EmailVerification;
import com.project.humanresource.entity.Employee;
import com.project.humanresource.repository.EmailVerificationRepository;

// Java Mail API (javax.mail değil, burada jakarta.mail kullanılmış)
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

// Spring service olarak tanımlama ve bağımlılık enjeksiyonu
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service // Bu sınıf bir servis sınıfıdır, Spring context'e otomatik dahil olur
@RequiredArgsConstructor // final alanları otomatik constructor ile enjekte eder
public class EmailVerificationService {

    private final EmailVerificationRepository repository;

    public void generateAndSendCode(Employee employee) {
        // UUID kod oluştur ama kullanıcıya gösterme
        EmailVerification verification = new EmailVerification(employee);
        repository.save(verification);

        // Sadece bilgi amaçlı mail gönder
        sendVerificationEmail(employee.getEmailWork());
    }

    private void sendVerificationEmail(String toEmail) {
        String message = "Your email verification request has been received.\n"
                + "Please log in to your account and click the verify button.";

        // veya doğrudan bir link de olabilir:
        // String verificationUrl = "http://localhost:9090/api/auth/verify";

        // javax.mail ile gönderim yapılır (yukarıda verdiğimiz gibi)
    }

}


