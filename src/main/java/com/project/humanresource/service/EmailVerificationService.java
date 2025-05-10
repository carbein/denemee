package com.project.humanresource.service;

import com.project.humanresource.entity.EmailVerification;
import com.project.humanresource.entity.Employee;
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

            // email verification nesnesi oluşturuluyor
            EmailVerification verification = new EmailVerification();
            verification.setEmail(toEmail);
            verification.setToken(token);
            verification.setExpiryDate(expiryDate);
            repository.save(verification);

            sendEmail(toEmail, token);
        }

        private void sendEmail(String toEmail, String token) {
            // SMTP ayarlarının tanılanması:
            // Bu ayarlar, Gmail’in SMTP (Mail Gönderim) sunucusuna bağlanmak için gerekli.
            // TLS protokolü (güvenli bağlantı) ve kimlik doğrulama (auth) açılmış.
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Session.getInstance(...): Bu ayarlarla oturum (bağlantı ortamı) oluşturur
            // session: Artık bu session üzerinden e-posta gönderimi yapılabilir
            Session session = Session.getInstance(props, // props: SMTP sunucusu için gerekli ayarları içerir (host, port, TLS vb.)
                    new Authenticator() { // Authenticator: Sunucuya giriş için kullanıcı adı ve şifreyi sağlar
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("elifcangoktepe@gmail.com", "jynohncfzxegpmrz");
                        }
                    });
            /**
             * 🧠 Gerçek dünya benzetmesi:
             * 📫 E-posta göndermek bir posta ofisine gitmek gibidir.
             * props → hangi postaneye gideceğini (adres, güvenlik kuralı) belirler.
             * Authenticator → postanedeki hesabını göstermek için kimliğini (mail ve şifre) kullanır.
             * Session → bu kimlik ve kurallarla oraya bağlanmış bir "oturumdur", yani artık işlem yapmaya hazırsındır.
             */

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
        if (optional.isEmpty()) {
            return false;
        }

        EmailVerification verification = optional.get();

        if (verification.getExpiryDate().isBefore(LocalDateTime.now())) {
            return false; // Token süresi dolmuş
        }
//        BURADA EMPLOYEE YE AKTİFLİK EKELYİP AKTİF HALE GETİRMEMİZ GEREKİYOR.
//        Employee employee = verification.getEmployee();
//        employee.setEnable(true);
//        employeeRepository.save(employee); // enable true yap ve kaydet

        return true;
    }
}



