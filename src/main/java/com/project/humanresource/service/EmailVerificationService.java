package com.project.humanresource.service;

import com.project.humanresource.entity.EmailVerification;

import com.project.humanresource.entity.User;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.EmailVerificationRepository;

import com.project.humanresource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final EmailVerificationRepository repository;
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String fromEmail;


    public void sendVerificationEmail(String toEmail) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(2);

        // email verification nesnesi oluşturuluyor
        EmailVerification verification = EmailVerification.builder()
                .email(toEmail)
                .token(token)
                .expiryDate(expiryDate)
                .isUsed(false)
                .build();
        repository.save(verification);
        sendEmail(toEmail, token);


    }
    // JAVAMAILSENDER ILE

    private void sendEmail(String toEmail, String token) {


        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
//           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Email Verification");
            message.setText("Click the link to verify your email: " +
                    "http://localhost:9090/api/auth/verify-email?token=" + token);

            mailSender.send(message);
        } catch (MailException e) {
            // Hataları logla ama uygulamayı durdurma

            throw new HumanResourceException(ErrorType.MAIL_SEND_FAILED);
        }


    }

    public boolean verifyToken(String token) {
        Optional<EmailVerification> optional = repository.findByToken(token);
        if (optional.isEmpty()) {
            throw new HumanResourceException(ErrorType.INVALID_TOKEN);
        }

        EmailVerification verification = optional.get();

        if (verification.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new HumanResourceException(ErrorType.EXPIRED_TOKEN);
//            return false; // Token süresi dolmuş
        }

        if (verification.isUsed()) throw new HumanResourceException(ErrorType.USED_TOKEN);

        User user = userRepository.findByEmail(verification.getEmail())
                .orElseThrow(() -> new HumanResourceException(ErrorType.USER_NOT_FOUND));

        user.setVerified(true);
        userRepository.save(user);

        verification.setUsed(true);
        repository.save(verification);

        return true;
    }


    public void sendPasswordResetEmail(String email){
        String token=UUID.randomUUID().toString();
        LocalDateTime expiryDate=LocalDateTime.now().plusDays(7);

        EmailVerification sendPassword= EmailVerification.builder()
                .email(email)
                .token(token)
                .isUsed(false)
                .expiryDate(expiryDate)
                .build();

        repository.save(sendPassword);

        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setSubject("Şifre Belirleme");
            message.setText("Şifrenizi belirlemek için bağlantıya tıklayın: " +
                    "http://localhost:9090/api/set-password?token=" + token);
            mailSender.send(message);

        } catch (MailException e) {
            throw new HumanResourceException(ErrorType.MAIL_SEND_FAILED);
        }
    }

    public String consumeTokenForPasswordReset(String token){
        EmailVerification verification=repository.findByToken(token)
                .orElseThrow(()->new HumanResourceException(ErrorType.INVALID_TOKEN));
        if (verification.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new HumanResourceException(ErrorType.EXPIRED_TOKEN);
        }

        if (verification.isUsed()){
            throw new HumanResourceException(ErrorType.USED_TOKEN);
        }
        verification.setUsed(true);
        repository.save(verification);
        return verification.getEmail();
    }

    public void setPassword (String token,String password){

    }


}



