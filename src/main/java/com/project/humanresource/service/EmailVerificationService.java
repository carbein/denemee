package com.project.humanresource.service;

import com.project.humanresource.entity.EmailVerification;
import com.project.humanresource.repository.EmailVerificationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

        @Value("${spring.mail.username}")
        private String fromEmail;

        private final EmailVerificationRepository repository;

        public void sendVerificationEmail(String toEmail) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(30);

            EmailVerification verification = new EmailVerification();
            verification.setEmail(toEmail);
            verification.setToken(token);
            verification.setExpiryDate(expiryDate);
            repository.save(verification);

            sendEmail(toEmail, token);
        }

        private void sendEmail(String toEmail, String token) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("elifcangoktepe@gmail.com", "humanresource**");
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject("Email Verification");
                message.setText("Click the link to verify your email: " +
                        "http://localhost:9090/api/verify?token=" + token);

                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean verifyToken(String token) {
            Optional<EmailVerification> optional = repository.findByToken(token);
            if (optional.isPresent()) {
                EmailVerification verification = optional.get();
                if (verification.getExpiryDate().isAfter(LocalDateTime.now())) {
                    return true;
                }
            }
            return false;
       }
}



