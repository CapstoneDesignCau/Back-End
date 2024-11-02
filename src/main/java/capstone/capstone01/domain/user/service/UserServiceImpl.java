package capstone.capstone01.domain.user.service;

import capstone.capstone01.domain.storage.domain.FileSaveInfo;
import capstone.capstone01.domain.storage.domain.enums.FileCategory;
import capstone.capstone01.domain.storage.service.StorageService;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.dto.request.*;
import capstone.capstone01.domain.user.dto.response.LoginResponseDto;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.domain.user.dto.response.UserInfoResponseDto;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.auth.JwtTokenUtil;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.UserConverter;
import capstone.capstone01.global.util.value.StaticValue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StorageService storageService;
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

    @Override
    public Long updateNickname(String email, NicknameUpdateRequestDto nicknameUpdateRequestDto) {
        User user = getLoginUserByEmail(email);
        if (user == null) {
            throw new UserException(ErrorStatus.USER_NOT_FOUND);
        }
        user.updateNickname(nicknameUpdateRequestDto.getNickname());
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long updateProfile(String email, MultipartFile profileImage) {
        User user = getLoginUserByEmail(email);
        if (user == null) {
            throw new UserException(ErrorStatus.USER_NOT_FOUND);
        }

        if (profileImage != null) {
            FileSaveInfo fileSaveInfo = storageService.updateFile(user.getProfileImage(), profileImage, FileCategory.USER_PROFILE);
            user.updateProfileImage(fileSaveInfo);
        }

        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long setDefaultProfileImage(String email) {
        User user = getLoginUserByEmail(email);
        if (user == null) {
            throw new UserException(ErrorStatus.USER_NOT_FOUND);
        }

        if (user.getProfileImage() != null) {
            storageService.deleteFile(user.getProfileImage());
            user.updateProfileImage(null);
        }

        userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public UserRole getUserRole(String email) {
        User user = getLoginUserByEmail(email);
        if (user == null) {
            throw new UserException(ErrorStatus.USER_NOT_FOUND);
        }
        return user.getRole();
    }

    @Override
    public Long updatePassword(String email, PasswordUpdateRequestDto passwordUpdateRequestDto) {
        User user = getLoginUserByEmail(email);
        if (user == null) {
            throw new UserException(ErrorStatus.USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(passwordUpdateRequestDto.getCurrentPassword(), user.getPassword())) {
            throw new UserException(ErrorStatus.USER_INCORRECT_PW);
        }

        user.setPassword(passwordEncoder.encode(passwordUpdateRequestDto.getNewPassword()));
        userRepository.save(user);
        return user.getId();

    }

    @Override
    public UserInfoResponseDto getUserInfo(String email) {
        User user = getLoginUserByEmail(email);
        if (user == null) {
            throw new UserException(ErrorStatus.USER_NOT_FOUND);
        }

        return UserConverter.toUserInfoResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public String getProfileImageUrl(String email) {
        User user = getLoginUserByEmail(email);
        if (user == null) {
            throw new UserException(ErrorStatus.USER_NOT_FOUND);
        }

        FileSaveInfo profileImage = user.getProfileImage();
        return profileImage != null ? profileImage.getFileUrl() : StaticValue.DEFAULT_PROFILE_IMAGE_URL;
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