package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.domain.dto.response.LoginResponseDto;

public class UserConverter {

    public static User toUser(UserSignUpRequestDto requestDto) {
        return User.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .nickname(requestDto.getNickname())
                .gender(requestDto.getGender())
                .birthday(requestDto.getBirthday())
                .build();
    }

    public static LoginResponseDto toLoginResponseDto(String token) {
        return LoginResponseDto.builder()
                .accessToken(token)
                .build();
    }

}
