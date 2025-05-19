package com.project.humanresource.controller;

import com.project.humanresource.dto.request.AddCompanyBranchRequestDto;
import com.project.humanresource.dto.response.BaseResponse;
import com.project.humanresource.entity.BaseEntity;
import com.project.humanresource.service.CompanyBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping()
@CrossOrigin("*")
public class CompanyBranchController {

    private final CompanyBranchService companyBranchService;

    @PostMapping("/branch/add")
    public ResponseEntity<BaseResponse<Boolean>> addBranch(AddCompanyBranchRequestDto dto){
        companyBranchService.addBranch(dto);

        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .data(true)
                        .message("Şube başarıyla eklendi.")
                .build());
    }
}
