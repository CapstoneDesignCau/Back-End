package capstone.capstone01.domain.user.service;

import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.dto.request.LoginRequestDto;
import capstone.capstone01.domain.user.dto.request.UserSignUpRequestDto;
import capstone.capstone01.domain.user.dto.response.LoginResponseDto;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.auth.JwtTokenUtil;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${spring.jwt.secret}")
    private String secretKey; //JWT 비밀키

    @Value("${spring.jwt.expired-time}")
    private Long expiredMs; //JWT 토큰 수명

    @Override
    public Long signUp(UserSignUpRequestDto userSignUpRequestDto) {
        validateUserCreation(userSignUpRequestDto.getEmail(), userSignUpRequestDto.getNickname());

        String encodedPassword = passwordEncoder.encode(userSignUpRequestDto.getPassword());

        User user = UserConverter.toUser(userSignUpRequestDto, encodedPassword);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = getUserByEmail(loginRequestDto.getEmail());

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new UserException(ErrorStatus.USER_INCORRECT_PW);
        }

        String token = JwtTokenUtil.createToken(user.getEmail(), secretKey, expiredMs);
        return UserConverter.toLoginResponseDto(token);
    }

    @Transactional(readOnly = true)
    public User getLoginUserById(Long id) {
        if(id == null) return null;

        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);

    }

    @Transactional(readOnly = true)
    public User getLoginUserByEmail(String email){
        if(email == null){
            throw new UserException(ErrorStatus.USER_EMAIL_NULL);
        }

        return getUserByEmail(email);
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