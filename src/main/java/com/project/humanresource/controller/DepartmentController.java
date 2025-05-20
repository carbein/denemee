package com.project.humanresource.controller;

import com.project.humanresource.dto.request.AddDepartmentRequesDto;
import com.project.humanresource.dto.response.BaseResponse;
import com.project.humanresource.entity.Department;
import com.project.humanresource.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping()
@CrossOrigin("*")
public class DepartmentController {

    public final DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<Boolean>> addDepartment(@RequestBody AddDepartmentRequesDto dto) {
        departmentService.addDepartment(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Departman başarıyla eklendi")
                        .data(true)
                .build());
    }
}
