package com.project.humanresource.repository;

import com.project.humanresource.entity.PersonelFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonelFileRepository extends JpaRepository<PersonelFile,Long> {

    Optional<PersonelFile> findByEmployeeId(long employeeId);       //
}
