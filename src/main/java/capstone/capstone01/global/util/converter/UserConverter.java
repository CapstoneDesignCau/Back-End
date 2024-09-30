package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.dto.response.LoginResponseDto;
import capstone.capstone01.domain.user.domain.enums.UserRole;

public class UserConverter {

    public static User toUser(UserSignUpRequestDto requestDto,String EncodedPassword){
        return User.builder()
                .email(requestDto.getEmail())
                .password(EncodedPassword)
                .name(requestDto.getName())
                .nickname(requestDto.getNickname())
                .gender(requestDto.getGender())
                .birthday(requestDto.getBirthday())
                .role(UserRole.USER)
                .build();
    }

    public static LoginResponseDto toLoginResponseDto(String token) {
        return LoginResponseDto.builder()
                .accessToken(token)
                .build();
    }

}
