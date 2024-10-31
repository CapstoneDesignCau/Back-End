package capstone.capstone01.global.util.converter;

import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.dto.response.LoginResponseDto;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.dto.response.UserInfoResponseDto;
import capstone.capstone01.global.util.value.StaticValue;

import static capstone.capstone01.global.util.value.StaticValue.DEFAULT_PROFILE_IMAGE_URL;


public class UserConverter {

    public static User toUser(UserSignUpRequestDto requestDto, String encodedPassword) {
        return User.builder()
                .email(requestDto.getEmail())
                .password(encodedPassword)
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

    public static UserInfoResponseDto toUserInfoResponseDto(User user) {
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .profileImageUrl(user.getProfileImage() != null ? user.getProfileImage().getFileUrl() : DEFAULT_PROFILE_IMAGE_URL)
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .build();
    }
}