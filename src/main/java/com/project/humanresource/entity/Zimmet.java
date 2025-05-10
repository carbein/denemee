package com.project.humanresource.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "zimmet")
public class Zimmet extends BaseEntity {
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ZimmetKategori kategori;

    @Column(nullable = false, unique = true)
    private String serino;

    @Column(nullable = false)
    private LocalDate verilisTarihi;

    private LocalDate iadeTarihi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calisan_id", nullable = false)
    private Employee calisan;
}

// ZimmetKategori enum'u da aynı dosyada tanımlanıyor:
enum ZimmetKategori {
    BILGISAYAR, TELEFON, DIGER
} 