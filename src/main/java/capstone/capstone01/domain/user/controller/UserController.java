package capstone.capstone01.domain.user.controller;

import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.dto.request.LoginRequestDto;
import capstone.capstone01.domain.user.dto.request.NicknameUpdateRequestDto;
import capstone.capstone01.domain.user.dto.request.PasswordUpdateRequestDto;
import capstone.capstone01.domain.user.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.dto.response.LoginResponseDto;
import capstone.capstone01.domain.user.dto.response.UserInfoResponseDto;
import capstone.capstone01.domain.user.service.UserService;
import capstone.capstone01.global.apipayload.CustomApiResponse;
import capstone.capstone01.global.apipayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "사용자 회원가입 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/signUp")
    public CustomApiResponse<Long> signUp(
            @Valid @RequestBody UserSignUpRequestDto userSignUpRequestDto
    ) {
        Long userId = userService.signUp(userSignUpRequestDto);
        return CustomApiResponse.of(SuccessStatus.USER_CREATED, userId);
    }

    @Operation(summary = "로그인", description = "사용자 로그인 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/login")
    public CustomApiResponse<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return CustomApiResponse.of(SuccessStatus.USER_OK, loginResponseDto);
    }

    @Operation(summary = "닉네임 중복 확인", description = "닉네임 중복 확인 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/check-duplicate/nickname")
    public CustomApiResponse<Boolean> checkNicknameDuplicate(
            @RequestParam String nickname
    ) {
        Boolean isNicknameDuplicate = userService.isNicknameDuplicate(nickname);
        return CustomApiResponse.of(SuccessStatus.USER_OK, isNicknameDuplicate);
    }

    @Operation(summary = "이메일 중복 확인", description = "이메일 중복 확인 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/check-duplicate/email")
    public CustomApiResponse<Boolean> checkEmailDuplicate(
            @RequestParam String email
    ) {
        Boolean isEmailDuplicate = userService.isEmailDuplicate(email);
        return CustomApiResponse.of(SuccessStatus.USER_OK, isEmailDuplicate);
    }

    @Operation(summary = "유저 역할 조회", description = "유저 역할 조회 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/role")
    public CustomApiResponse<UserRole> getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserRole userRole = userService.getUserRole(email);
        return CustomApiResponse.of(SuccessStatus.USER_OK, userRole);
    }

    @Operation(summary = "닉네임 업데이트", description = "사용자 닉네임 업데이트 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/nickname")
    public CustomApiResponse<Long> updateNickname(
            @Valid @RequestBody NicknameUpdateRequestDto nicknameUpdateRequestDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Long userId = userService.updateNickname(email, nicknameUpdateRequestDto);
        return CustomApiResponse.of(SuccessStatus.USER_OK, userId);
    }

    @Operation(summary = "프로필 업데이트", description = "사용자 프로필 업데이트 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "/profile", consumes = "multipart/form-data")
    public CustomApiResponse<Long> updateProfile(
            @RequestPart("profileImage") MultipartFile profileImage
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Long userId = userService.updateProfile(email, profileImage);
        return CustomApiResponse.of(SuccessStatus.USER_OK, userId);
    }

    @Operation(summary = "기본 프로필 이미지로 설정", description = "기본 프로필 이미지로 설정 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/profile/default")
    public CustomApiResponse<Long> setDefaultProfileImage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Long userId = userService.setDefaultProfileImage(email);
        return CustomApiResponse.of(SuccessStatus.USER_OK, userId);
    }

    @Operation(summary = "비밀번호 업데이트", description = "비밀번호 업데이트 API")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/password")
    public CustomApiResponse<Long> updatePassword(
            @Valid @RequestBody PasswordUpdateRequestDto passwordUpdateRequestDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Long userId = userService.updatePassword(email, passwordUpdateRequestDto);
        return CustomApiResponse.of(SuccessStatus.USER_OK, userId);
    }

    @Operation(summary = "유저 정보 조회", description = "유저 정보 조회 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/info")
    public CustomApiResponse<UserInfoResponseDto> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserInfoResponseDto userInfo = userService.getUserInfo(email);
        return CustomApiResponse.of(SuccessStatus.USER_OK, userInfo);
    }

    @Operation(summary = "프로필 이미지 URL 조회", description = "프로필 이미지 URL 조회 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/profile/image")
    public CustomApiResponse<String> getProfileImageUrl() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        String profileImageUrl = userService.getProfileImageUrl(email);
        return CustomApiResponse.of(SuccessStatus.USER_OK, profileImageUrl);
    }

    @Operation(summary = "닉네임 조회", description = "사용자 닉네임 조회 API")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/nickname")
    public CustomApiResponse<String> getNickname() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        String nickname = userService.getNickname(email);
        return CustomApiResponse.of(SuccessStatus.USER_OK, nickname);
    }

}