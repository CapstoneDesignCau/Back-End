package capstone.capstone01.domain.user.service;

import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.dto.request.*;
import capstone.capstone01.domain.user.dto.response.LoginResponseDto;
import capstone.capstone01.domain.user.dto.response.UserInfoResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Long signUp(UserSignUpRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto requestDto);

    Boolean isNicknameDuplicate(String nickname);

    Boolean isEmailDuplicate(String email);

    User getLoginUserById(Long id);

    User getLoginUserByEmail(String email);

    Long updateNickname(String email, NicknameUpdateRequestDto nicknameUpdateRequestDto);

    Long updateProfile(String email, MultipartFile profileImage);

    Long setDefaultProfileImage(String email);

    UserRole getUserRole(String email);

    Long updatePassword(String email, PasswordUpdateRequestDto passwordUpdateRequestDto);

    UserInfoResponseDto getUserInfo(String email);

    String getProfileImageUrl(String email);
}