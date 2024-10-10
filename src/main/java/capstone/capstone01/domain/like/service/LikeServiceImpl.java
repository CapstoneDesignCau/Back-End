package capstone.capstone01.domain.like.service;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.comment.domain.repository.CommentRepository;
import capstone.capstone01.domain.imagepost.domain.ImagePost;
import capstone.capstone01.domain.imagepost.domain.repository.ImagePostRepository;
import capstone.capstone01.domain.like.domain.CommentLike;
import capstone.capstone01.domain.like.domain.ImagePostLike;
import capstone.capstone01.domain.like.domain.repository.CommentLikeRepository;
import capstone.capstone01.domain.like.domain.repository.ImagePostLikeRepository;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.CommentException;
import capstone.capstone01.global.exception.specific.ImagePostException;
import capstone.capstone01.global.exception.specific.LikeException;
import capstone.capstone01.global.exception.specific.UserException;
import capstone.capstone01.global.util.converter.LikeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService {

    private final ImagePostLikeRepository imagePostLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final ImagePostRepository imagePostRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public void likeImagePost(String email, Long imagePostId) {
        User user = getUserByEmail(email);
        ImagePost imagePost = getImagePostById(imagePostId);

        ImagePostLike imagePostLike;
        if (isImagePostAlreadyLiked(user, imagePostId)) {
            imagePostLike = getImagePostLike(user, imagePostId);
            if (imagePostLike.getIsDeleted()) {
                imagePostLike.setIsDeleted(false);
            } else {
                throw new LikeException(ErrorStatus.POST_ALREADY_LIKE);
            }
        } else {
            imagePostLike = LikeConverter.toImagePostLike(user, imagePost);
        }

        imagePostLikeRepository.save(imagePostLike);
    }

    @Override
    public void cancelLikeImagePost(String email, Long imagePostId) {
        User user = getUserByEmail(email);

        ImagePostLike imagePostLike = getImagePostLike(user, imagePostId);

        if (imagePostLike.getIsDeleted()) {
            throw new LikeException(ErrorStatus.POST_LIKE_ALREADY_DELETED);
        }

        imagePostLike.setIsDeleted(true);
        imagePostLikeRepository.save(imagePostLike);
    }

    @Override
    public void likeComment(String email, Long commentId) {
        User user = getUserByEmail(email);
        Comment comment = getCommentById(commentId);

        CommentLike commentLike;
        if (isCommentAlreadyLiked(user, commentId)) {
            commentLike = getCommentLike(user, commentId);
            if (commentLike.getIsDeleted()) {
                commentLike.setIsDeleted(false);
            } else {
                throw new LikeException(ErrorStatus.COMMENT_ALREADY_LIKE);
            }
        } else {
            commentLike = LikeConverter.toCommentLike(user, comment);
        }

        commentLikeRepository.save(commentLike);
    }

    @Override
    public void cancelLikeComment(String email, Long commentId) {
        User user = getUserByEmail(email);

        CommentLike commentLike = getCommentLike(user, commentId);

        if (commentLike.getIsDeleted()) {
            throw new LikeException(ErrorStatus.COMMENT_LIKE_ALREADY_DELETED);
        }

        commentLike.setIsDeleted(true);
        commentLikeRepository.save(commentLike);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
    }

    private ImagePost getImagePostById(Long imagePostId) {
        return imagePostRepository.findById(imagePostId).orElseThrow(() -> new ImagePostException(ErrorStatus.POST_NOT_FOUND));
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentException(ErrorStatus.COMMENT_NOT_FOUND));
    }

    private Boolean isImagePostAlreadyLiked(User user, Long imagePostId) {
        return imagePostLikeRepository.existsByImagePostIdAndUserId(imagePostId, user.getId());
    }

    private Boolean isCommentAlreadyLiked(User user, Long commentId) {
        return commentLikeRepository.existsByCommentIdAndUserId(commentId, user.getId());
    }

    private ImagePostLike getImagePostLike(User user, Long imagePostId) {
        return imagePostLikeRepository.findByImagePostIdAndUserId(imagePostId, user.getId()).orElseThrow(() -> new LikeException(ErrorStatus.POST_LIKE_NOT_FOUND));
    }

    private CommentLike getCommentLike(User user, Long commentId) {
        return commentLikeRepository.findByCommentIdAndUserId(commentId, user.getId()).orElseThrow(() -> new LikeException(ErrorStatus.COMMENT_LIKE_NOT_FOUND));
    }
}