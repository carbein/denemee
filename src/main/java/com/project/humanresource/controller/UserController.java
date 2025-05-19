package com.project.humanresource.controller;

import com.project.humanresource.dto.request.AddLoginRequestDto;
import com.project.humanresource.dto.request.AddRegisterRequestDto;
import com.project.humanresource.dto.request.RegisterApproveDto;
import com.project.humanresource.dto.request.SetPasswordRequestDto;
import com.project.humanresource.dto.response.BaseResponse;
import com.project.humanresource.dto.response.UserResponseDto;
import com.project.humanresource.entity.User;
import com.project.humanresource.exception.ErrorType;
import com.project.humanresource.exception.HumanResourceException;
import com.project.humanresource.service.EmailVerificationService;
import com.project.humanresource.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailVerificationService emailVerificationService;

    //  Kayıt Başvurusu (Register +Şirket)
    @PostMapping("/register")
    public ResponseEntity<BaseResponse<Boolean>> doRegister(@RequestBody AddRegisterRequestDto dto){
        if(!dto.password().equals(dto.rePassword())) throw new HumanResourceException(ErrorType.PASSWORD_MISMATCH);
        userService.register(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .data(true)
                        .message("Kayıt başarılı.Onay bekleniyor.")
                .build());
    }

    //     Giriş işlemi
    @PostMapping("login")
    public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody AddLoginRequestDto dto){

        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .code(200)
                        .data(userService.login(dto))
                        .message("Başarıyla giriş yaptınız.")
                .build());
    }

    //      Admin tarafından manager + şirket onayı için
    @PostMapping("/approve")
    public ResponseEntity<BaseResponse<Boolean>> approve(@RequestBody RegisterApproveDto dto){
        userService.approveUser(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Onay işlemi tamamlandı.")
                        .data(true)
                .build());
    }

    //      Onay bekleyen şifrket yöneticleri listesi ,admin sayfası için
    @GetMapping("/pending")
    public ResponseEntity<BaseResponse<List<UserResponseDto>>> pendingUsers(){
        List<UserResponseDto> pendingList=userService.getPendingUsers();
        return ResponseEntity.ok(BaseResponse.<List<UserResponseDto>>builder()
                        .code(200)
                        .message("Onay bekleyen kullanıcılar listelendi")
                        .data(pendingList)
                .build());

    }


    @PostMapping("(set-password")
    public ResponseEntity<BaseResponse<Boolean>> setPassword(@RequestBody @Valid SetPasswordRequestDto dto) {
        if (!dto.password().equals(dto.rePassword())) throw new HumanResourceException(ErrorType.PASSWORD_MISMATCH);

        String email=emailVerificationService.consumeTokenForPasswordReset(dto.token());
        userService.completeUserRegistration(email,dto.password());

        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Şifre Başarıyla oluşturuldu")
                        .data(true)
                .build());


    }











} 