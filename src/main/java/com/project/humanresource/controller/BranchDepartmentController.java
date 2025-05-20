package com.project.humanresource.controller;

import com.project.humanresource.dto.request.AssignDepartmentToBranchRequestDto;
import com.project.humanresource.dto.response.BaseResponse;
import com.project.humanresource.dto.response.BranchDepartmentResponseDto;
import com.project.humanresource.service.BranchDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
//@PreAuthorize("hasAuthority('COMPANY_ADMIN')")
public class BranchDepartmentController {

    private final BranchDepartmentService branchDepartmentService;

    @PostMapping("/branch-department")
    public ResponseEntity<BaseResponse<Boolean>> assingDepartment(@RequestBody AssignDepartmentToBranchRequestDto dto){
        branchDepartmentService.assignDepartmentBranch(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Departman şubeye başarıyla aktarıldı")
                        .data(true)
                .build());
    }

    @GetMapping("/{branchId}/departments")
    public ResponseEntity<BaseResponse<List<BranchDepartmentResponseDto>>> getDeparments(@PathVariable Long branchId){
        List<BranchDepartmentResponseDto> departmens=branchDepartmentService.getDepartmentBranchId(branchId);
        return ResponseEntity.ok(BaseResponse.<List<BranchDepartmentResponseDto>>builder()
                        .code(200)
                        .message("Şubeye bağlı departmentler listelendi")
                        .data (departmens)
                .build());
    }

}
