package com.project.humanresource.entity;

import com.project.humanresource.utility.AssignmentCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tblassignment")
public class Assignment extends BaseEntity {

    String description;
    AssignmentCategory category;
    String serialNumber;
    LocalDate assignmentDate;
    LocalDate returnDate;
    Long employeeId;
}

