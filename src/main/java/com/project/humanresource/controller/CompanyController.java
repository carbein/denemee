package com.project.humanresource.controller;


import com.project.humanresource.dto.request.UpdateCompanyRequestDto;
import com.project.humanresource.dto.response.BaseResponse;
import com.project.humanresource.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.project.humanresource.config.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
@CrossOrigin("*")
public class CompanyController {

    private final CompanyService companyService;

    @PreAuthorize("hasAuthority('COMPANY_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<Boolean>> updateCompany(@RequestBody @Valid UpdateCompanyRequestDto dto){
        companyService.updateCompany(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Şirket bilgileri güncellendi")
                        .data(true)
                .build());
    }
}
