package capstone.capstone01.domain.comment.service;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.comment.domain.repository.CommentRepository;
import capstone.capstone01.domain.comment.dto.request.CommentCreateRequestDto;
import capstone.capstone01.domain.comment.dto.response.CommentResponseDto;
import capstone.capstone01.domain.like.domain.repository.CommentLikeRepository;
import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.post.domain.repository.PostRepository;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.CommentException;
import capstone.capstone01.global.exception.specific.PostException;
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
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Long createComment(String email, CommentCreateRequestDto commentCreateRequestDto) {
        User user = findUserByEmail(email);
        Post post = findPostById(commentCreateRequestDto.getPostId());

        Comment comment = CommentConverter.toComment(commentCreateRequestDto, user, post);
        post.addComment(comment);
        commentRepository.save(comment);
        postRepository.save(post);
        return comment.getId();
    }


    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long id, String userEmail) {
        Comment comment = findCommentById(id);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        boolean isLikedByUser = commentLikeRepository.existsByCommentAndUserAndIsDeletedFalse(comment, user);

        return CommentConverter.toCommentResponseDto(comment, isLikedByUser);
    }

    @Override
    public void deleteComment(String email, Long id) {
        User user = findUserByEmail(email);
        Comment comment = findCommentById(id);

        if (user.getRole() == UserRole.ADMIN || comment.getWriter().getEmail().equals(email)) {
            Post post = comment.getPost();
            post.removeComment(comment);
            comment.delete(true);
            commentRepository.save(comment);
            postRepository.save(post);
        } else {
            throw new CommentException(ErrorStatus.COMMENT_DELETE_NOT_ALLOWED);
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostException(ErrorStatus.POST_NOT_FOUND));
    }

    private Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentException(ErrorStatus.COMMENT_NOT_FOUND));
    }
}