package capstone.capstone01.domain.user.service;

import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.dto.request.LoginRequestDto;
import capstone.capstone01.domain.user.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.dto.response.LoginResponseDto;

public interface UserService {

    Long signUp(UserSignUpRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto requestDto);

    Boolean isNicknameDuplicate(String nickname);

    Boolean isEmailDuplicate(String email);

    User getLoginUserById(Long id);

    User getLoginUserByEmail(String email);
}