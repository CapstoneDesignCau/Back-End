package capstone.capstone01.domain.comment.service;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.comment.domain.repository.CommentRepository;
import capstone.capstone01.domain.comment.dto.request.CommentCreateRequestDto;
import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;
import capstone.capstone01.domain.imagepost.domain.ImagePost;
import capstone.capstone01.domain.imagepost.domain.repository.ImagePostRepository;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.CommentException;
import capstone.capstone01.global.exception.specific.ImagePostException;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.CommentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ImagePostRepository imagePostRepository;

    @Override
    public Long createComment(String email, CommentCreateRequestDto commentCreateRequestDto) {
        User user = findUserByEmail(email);
        ImagePost imagePost = findImagePostById(commentCreateRequestDto.getImagePostId());

        Comment comment = CommentConverter.toComment(commentCreateRequestDto, user, imagePost);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponseDto getComment(String email, Long id) {
        Comment comment = findCommentById(id);

        return CommentConverter.toCommentResponseDto(comment);
    }

    @Override
    public void deleteComment(String email, Long id) {
        User user = findUserByEmail(email);
        Comment comment = findCommentById(id);

        if (user.getRole() == UserRole.ADMIN || comment.getWriter().getEmail().equals(email)) {
            ImagePost imagePost = comment.getImagePost();
            imagePost.removeComment(comment); // Ensure bidirectional mapping
            comment.delete(true);
            commentRepository.save(comment);
        } else {
            throw new CommentException(ErrorStatus.COMMENT_DELETE_NOT_ALLOWED);
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

    private Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentException(ErrorStatus.COMMENT_NOT_FOUND));
    }
}