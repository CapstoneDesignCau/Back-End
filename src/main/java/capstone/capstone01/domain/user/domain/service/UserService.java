package capstone.capstone01.domain.user.domain.service;

import capstone.capstone01.domain.user.domain.dto.request.LoginRequestDto;
import capstone.capstone01.domain.user.domain.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.domain.dto.response.LoginResponseDto;
import lombok.RequiredArgsConstructor;

public interface UserService {

    Long signUp(UserSignUpRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto requestDto);

    Boolean isNicknameDuplicate(String nickname);

    Boolean isEmailDuplicate(String email);

}