package com.project.humanresource.service;

import com.project.humanresource.dto.request.AddEmployeeRequestDto;
import com.project.humanresource.entity.Employee;
import com.project.humanresource.entity.User;
import com.project.humanresource.repository.EmployeeRepository;
import com.project.humanresource.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Geçici: email → employeeId eşlemesi için in-memory map
    private static final ConcurrentHashMap<String, Long> emailToEmployeeMap = new ConcurrentHashMap<>();


    public void addEmployee( AddEmployeeRequestDto dto, Long companyId) {

            Employee employee= Employee.builder()
                    .firstName(dto.name())
                    .lastName(dto.surname())
                    .phoneWork(dto.phoneNumber())
                    .companyId(companyId)
                    .titleId(dto.titleId())
                    .userId(null)
                    .personalFiledId(null)


                    .build();

            employeeRepository.save(employee);

        // Eşleşme için email üzerinden kaydı sakla
            emailToEmployeeMap.put(dto.email(),employee.getId());

    }

    // Şifre oluşturulduktan sonra eşleştirme işlemi yapılır
    public void assignUserToEmployee(String email,Long userId){
        Long employeeId=emailToEmployeeMap.get(email);
        if (employeeId !=null){
                Employee employee=employeeRepository.findById(employeeId)
                        .orElseThrow(()->new RuntimeException("Employe bulunamadı"));
                employee.setUserId(userId);
                employeeRepository.save(employee);
                emailToEmployeeMap.remove(email); // eşleştirme tamamlandıysa map’ten sil
        }

    }
}
