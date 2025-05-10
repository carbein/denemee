package com.project.humanresource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblemailverification")
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    String email;
    String code; // UUID, kişi mailii girdiğinde random UUID oluştutrulup veri tabanına kaydedilecek. UUID.randomUUID() gibi bir metot ile
    LocalDateTime expiryDate;
    @OneToOne
    Employee employee;
    boolean state;

    public EmailVerification(Employee employee) {
        this.code = UUID.randomUUID().toString(); // ← kod burada üretiliyor
        this.employee = employee;
        this.expiryDate = LocalDateTime.now().plusHours(24);
    }
}
