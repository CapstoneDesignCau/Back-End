package capstone.capstone01.domain.user.domain.service;

import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.dto.request.LoginRequestDto;
import capstone.capstone01.domain.user.domain.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.domain.dto.response.LoginResponseDto;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specificException.UserException;
import capstone.capstone01.global.util.converter.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long signUp(UserSignUpRequestDto userSignUpRequestDto) {
        validateUserCreation(userSignUpRequestDto.getEmail(), userSignUpRequestDto.getNickname());

        User user = UserConverter.toUser(userSignUpRequestDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = getUserByEmail(loginRequestDto.getEmail());

        if (!loginRequestDto.getPassword().equals(user.getPassword())) {
            throw new UserException(ErrorStatus.USER_INCORRECT_PW);
        }

        // Todo: 임시 토큰 생성( Security 구현 JWT 로직 추가)
        String token = "dummy-token-for-" + user.getEmail();
        return UserConverter.toLoginResponseDto(token);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    private void validateUserCreation(String email, String nickname) {
        if (isEmailDuplicate(email)) {
            throw new UserException(ErrorStatus.USER_ALREADY_EXISTS);
        }
        if (isNicknameDuplicate(nickname)) {
            throw new UserException(ErrorStatus.USER_NICKNAME_EXISTS);
        }
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
    }

}