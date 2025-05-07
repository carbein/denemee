package com.project.humanresource.entity;

import com.project.humanresource.utility.ExpensesCategory;
import com.project.humanresource.utility.StateTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblexpenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal amount;
    String description;
    String documentUrl;
    StateTypes state;
    ExpensesCategory category;
    Long employeeId;

}
