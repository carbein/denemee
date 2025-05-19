package com.project.humanresource.service;

import com.project.humanresource.dto.request.AddEmployeeRequestDto;
import com.project.humanresource.dto.request.SetPersonelFileRequestDto;
import com.project.humanresource.entity.Employee;
import com.project.humanresource.entity.PersonalFile;
import com.project.humanresource.entity.User;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.repository.EmployeeRepository;
import com.project.humanresource.repository.PersonelFileRepository;
import com.project.humanresource.repository.UserRepository;
import com.project.humanresource.utility.UserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Geçici: email → employeeId eşlemesi için in-memory map
    private static final ConcurrentHashMap<String, Long> emailToEmployeeMap = new ConcurrentHashMap<>();
    private final UserRepository userRepository;
    private final PersonelFileRepository personelFileRepository;


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

    public void setPersonelFile( SetPersonelFileRequestDto dto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        if (!user.getUserRoleId().equals(UserStatus.Employee.ordinal())){
            throw new HumanResourceException(ErrorType.UNAUTHORIZED);
        }

        Employee employee=employeeRepository.findByUserId(user.getId())
                .orElseThrow(()->new HumanResourceException(ErrorType.USER_NOT_FOUND));

        if (personelFileRepository.existsById(employee.getId())){
            throw new HumanResourceException(ErrorType.DUPLICATE_PERSONAL_FILE);

        }
        PersonalFile personalFile= PersonalFile.builder()
                .gender(dto.gender())
                .birthdate(dto.birthdate())
                .personalPhone(dto.personalPhone())
                .personalEmail(dto.personalEmail())
                .nationalId(dto.nationalId())
                .educationLevel(dto.educationLevel())
                .maritalStatus(dto.maritalStatus())
                .bloodType(dto.bloodType())
                .numberOfChildren(dto.numberOfChildren())
                .address(dto.address())
                .city(dto.city())
                .iban(dto.iban())
                .bankName(dto.bankName())
                .bankAccountNumber(dto.bankAccountNumber())
                .bankAccountType(dto.bankAccountType())
                .employeeId(employee.getId())
                .build();

        personelFileRepository.save(personalFile);

    }
}
