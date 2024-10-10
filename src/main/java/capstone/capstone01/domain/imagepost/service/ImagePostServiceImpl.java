package capstone.capstone01.domain.imagepost.service;

import capstone.capstone01.domain.imagepost.domain.ImagePost;
import capstone.capstone01.domain.imagepost.domain.repository.ImagePostRepository;
import capstone.capstone01.domain.imagepost.dto.request.ImagePostCreateRequestDto;
import capstone.capstone01.domain.imagepost.dto.response.ImagePostResponseDto;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.ImagePostException;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.ImagePostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ImagePostServiceImpl implements ImagePostService {

    private final ImagePostRepository imagePostRepository;
    private final UserRepository userRepository;

    @Override
    public Long createImagePost(String email, ImagePostCreateRequestDto imagePostCreateRequestDto) {
        User user = findUserByEmail(email);
        ImagePost imagePost = ImagePostConverter.toImagePost(imagePostCreateRequestDto, user);

        imagePostRepository.save(imagePost);
        return imagePost.getId();
    }

    @Override
    public ImagePostResponseDto getImagePost(String email, Long id) {
        User user = findUserByEmail(email);
        ImagePost imagePost = findImagePostById(id);

        if (user.getRole() == UserRole.ADMIN || imagePost.getIsOpen() || imagePost.getWriter().getEmail().equals(email)) {
            return ImagePostConverter.toImagePostResponseDto(imagePost);
        } else {
            throw new ImagePostException(ErrorStatus.POST_READ_NOT_ALLOWED);
        }
    }

    @Override
    public void deleteImagePost(String email, Long id) {
        User user = findUserByEmail(email);
        ImagePost imagePost = findImagePostById(id);

        if (user.getRole() == UserRole.ADMIN || imagePost.getWriter().getEmail().equals(email)) {
            imagePost.delete(true);
            imagePostRepository.save(imagePost);
        } else {
            throw new ImagePostException(ErrorStatus.POST_DELETE_NOT_ALLOWED);
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
    }

    private ImagePost findImagePostById(Long id) {
        return imagePostRepository.findById(id)
                .orElseThrow(() -> new ImagePostException(ErrorStatus.POST_NOT_FOUND));
    }
}