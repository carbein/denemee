package com.project.humanresource.controller;

import com.project.humanresource.dto.request.AddShiftRequestDto;
import com.project.humanresource.dto.response.BaseResponse;
import com.project.humanresource.entity.Shift;
import com.project.humanresource.service.ShiftService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.humanresource.config.RestApis.SHIFT;

@RestController
@RequiredArgsConstructor
@RequestMapping()
@CrossOrigin("*")
@SecurityRequirement(name = "bearerAuth")
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping(SHIFT)
    public ResponseEntity<BaseResponse<Shift>> addShift(@RequestBody AddShiftRequestDto dto){
        return ResponseEntity.ok(BaseResponse.<Shift>builder()
                        .message("Shift added successfully")
                        .code(200)
                        .data(shiftService.addShift(dto))
                .build());
    }

}
