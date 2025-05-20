package com.project.humanresource.controller;

import com.project.humanresource.config.JwtTokenFilter;
import com.project.humanresource.dto.request.AddEmployeeRequestDto;
import com.project.humanresource.dto.request.AssignTitleToEmployeeRequestDto;
import com.project.humanresource.dto.request.SetPersonelFileRequestDto;
import com.project.humanresource.dto.response.BaseResponse;
import com.project.humanresource.entity.Company;
import com.project.humanresource.entity.User;
import com.project.humanresource.repository.CompanyRepository;
import com.project.humanresource.repository.UserRepository;
import com.project.humanresource.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.project.humanresource.config.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
@CrossOrigin("*")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JwtTokenFilter jwtTokenFilter;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @PreAuthorize("hasAuthority('COMPANY_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<BaseResponse<Boolean>> addEmployee (@RequestBody @Valid AddEmployeeRequestDto dto){

        // Login olan kullanıcının email bilgisi JWT'den alınır (SecurityContext üzerinden)
        String currentEmail= SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepository.findByEmail(currentEmail).orElseThrow(()->new RuntimeException("Kullanıcı bulunamadı"));

        Company company=companyRepository.findByUserId(user.getId()).orElseThrow(()-> new RuntimeException("Şirket bulunamadı"));

        employeeService.addEmployee(dto,company.getId());
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .message("Çalışan başarıyla eklendi.")
                .build());
    }
    //@PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("/personel-file")
    public ResponseEntity<BaseResponse<Boolean>> setPersonelFile(@RequestBody @Valid SetPersonelFileRequestDto dto){
        employeeService.setPersonelFile(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Özlük bilgileri başarıyla kaydedildi")
                        .data(true)
                .build());
    }

    //@PreAuthorize("hasAuthority('COMPANY_ADMIN')")
    @PostMapping("/assign-title")
    public ResponseEntity<BaseResponse<Boolean>> assignTitleToemployee(@RequestBody AssignTitleToEmployeeRequestDto dto){
        employeeService.assignTitleToEmployee(dto);

        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .data(true)
                        .message("Unvan çalışana başarıyla atandı")
                .build());
    }

   // @PreAuthorize("hasAuthority('COMPANY_ADMIN')")
    @PutMapping("/{employeeId}")
    public ResponseEntity<BaseResponse<Boolean>> deactiveEmployee(@PathVariable Long employeeId){
        employeeService.setEmployeeActiveStatus(employeeId,false);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Çalışan pasif hale getirildi")
                        .data(true)
                .build());
    }

    // @PreAuthorize("hasAuthority('COMPANY_ADMIN')")
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<BaseResponse<Boolean>> deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Çalışan silindi")
                        .data(true)
                .build());
    }

    //@PreAuthorize("hasAuthority('COMPANY_ADMIN')")
    @PutMapping("/activate/{employeeId}")
    public ResponseEntity<BaseResponse<Boolean>> activateEmployee(@PathVariable Long employeeId) {
        employeeService.setEmployeeActiveStatus(employeeId,true);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .message("Çalışan yeniden aktif hale getirildi.")
                .build());
    }

}
