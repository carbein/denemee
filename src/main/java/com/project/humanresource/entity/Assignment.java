package com.project.humanresource.entity;

import com.project.humanresource.utility.AssignmentCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private AssignmentCategory category;

    private String serialNumber;

    private LocalDate assignmentDate;

    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}

// ZimmetKategori enum'u da aynı dosyada tanımlanıyor:
enum ZimmetKategori {
    BILGISAYAR, TELEFON, DIGER
} 