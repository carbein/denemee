package com.project.humanresource.entity;

import com.project.humanresource.utility.AssignmentCategory;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class Assignment {
    private Long id;
    private String description;
    private AssignmentCategory category;
    private String serialNumber;
    private LocalDate assignmentDate;
    private LocalDate returnDate;
    private Employee employee;
}

