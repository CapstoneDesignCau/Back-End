package capstone.capstone01.domain.user.domain.controller;

import capstone.capstone01.domain.user.domain.dto.request.LoginRequestDto;
import capstone.capstone01.domain.user.domain.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.domain.dto.response.LoginResponseDto;
import capstone.capstone01.domain.user.domain.service.UserService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "사용자 회원가입 API")
    @PostMapping("/signUp")
    public CustomApiResponse<Long> signUp(
            @Valid @RequestBody UserSignUpRequestDto userSignUpRequestDto
    ) {
        Long userId = userService.signUp(userSignUpRequestDto);
        return CustomApiResponse.of(SuccessStatus.USER_CREATED, userId);
    }

    @Operation(summary = "로그인", description = "사용자 로그인 API")
    @PostMapping("/login")
    public CustomApiResponse<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return CustomApiResponse.of(SuccessStatus.USER_OK, loginResponseDto);
    }

    @Operation(summary = "닉네임 중복 확인", description = "닉네임 중복 확인 API")
    @GetMapping("/check-duplicate/nickname")
    public CustomApiResponse<Boolean> checkNicknameDuplicate(
            @RequestParam String nickname
    ) {
        Boolean isDuplicate = userService.isNicknameDuplicate(nickname);
        return CustomApiResponse.of(SuccessStatus.USER_OK, isDuplicate);
    }

    @Operation(summary = "이메일 중복 확인", description = "이메일 중복 확인 API")
    @GetMapping("/check-duplicate/email")
    public CustomApiResponse<Boolean> checkEmailDuplicate(
            @RequestParam String email
    ) {
        Boolean isDuplicate = userService.isEmailDuplicate(email);
        return CustomApiResponse.of(SuccessStatus.USER_OK, isDuplicate);
    }
}