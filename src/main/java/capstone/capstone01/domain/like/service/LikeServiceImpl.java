package capstone.capstone01.domain.like.service;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.comment.domain.repository.CommentRepository;
import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.post.domain.repository.PostRepository;
import capstone.capstone01.domain.like.domain.CommentLike;
import capstone.capstone01.domain.like.domain.PostLike;
import capstone.capstone01.domain.like.domain.repository.CommentLikeRepository;
import capstone.capstone01.domain.like.domain.repository.PostLikeRepository;
import capstone.capstone01.domain.user.domain.User;
import capstone.capstone01.domain.user.domain.repository.UserRepository;
import capstone.capstone01.global.apipayload.code.status.ErrorStatus;
import capstone.capstone01.global.exception.specific.CommentException;
import capstone.capstone01.global.exception.specific.PostException;
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

    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public void likePost(String email, Long postId) {
        User user = getUserByEmail(email);
        Post post = getPostById(postId);

        PostLike postLike;
        if (isPostAlreadyLiked(user, postId)) {
            postLike = getPostLike(user, postId);
            if (postLike.getIsDeleted()) {
                postLike.setIsDeleted(false);
                post.incrementLikeCount(); // 좋아요 수 증가
            } else {
                throw new LikeException(ErrorStatus.POST_ALREADY_LIKE);
            }
        } else {
            postLike = LikeConverter.toImagePostLike(user, post);
            post.incrementLikeCount(); // 좋아요 수 증가
        }

        postLikeRepository.save(postLike);
        postRepository.save(post);
    }

    @Override
    public void cancelLikePost(String email, Long postId) {
        User user = getUserByEmail(email);

        PostLike postLike = getPostLike(user, postId);

        if (postLike.getIsDeleted()) {
            throw new LikeException(ErrorStatus.POST_LIKE_ALREADY_DELETED);
        }

        postLike.setIsDeleted(true);
        Post post = getPostById(postId);
        post.decrementLikeCount(); // 좋아요 수 감소

        postLikeRepository.save(postLike);
        postRepository.save(post);
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
                comment.incrementLikeCount(); // 좋아요 수 증가
            } else {
                throw new LikeException(ErrorStatus.COMMENT_ALREADY_LIKE);
            }
        } else {
            commentLike = LikeConverter.toCommentLike(user, comment);
            comment.incrementLikeCount(); // 좋아요 수 증가
        }

        commentLikeRepository.save(commentLike);
        commentRepository.save(comment);
    }

    @Override
    public void cancelLikeComment(String email, Long commentId) {
        User user = getUserByEmail(email);

        CommentLike commentLike = getCommentLike(user, commentId);

        if (commentLike.getIsDeleted()) {
            throw new LikeException(ErrorStatus.COMMENT_LIKE_ALREADY_DELETED);
        }

        commentLike.setIsDeleted(true);
        Comment comment = getCommentById(commentId);
        comment.decrementLikeCount(); // 좋아요 수 감소

        commentLikeRepository.save(commentLike);
        commentRepository.save(comment);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
    }

    private Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostException(ErrorStatus.POST_NOT_FOUND));
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentException(ErrorStatus.COMMENT_NOT_FOUND));
    }

    private Boolean isPostAlreadyLiked(User user, Long postId) {
        return postLikeRepository.existsByPostIdAndUserId(postId, user.getId());
    }

    private Boolean isCommentAlreadyLiked(User user, Long commentId) {
        return commentLikeRepository.existsByCommentIdAndUserId(commentId, user.getId());
    }

    private PostLike getPostLike(User user, Long postId) {
        return postLikeRepository.findByPostIdAndUserId(postId, user.getId()).orElseThrow(() -> new LikeException(ErrorStatus.POST_LIKE_NOT_FOUND));
    }

    private CommentLike getCommentLike(User user, Long commentId) {
        return commentLikeRepository.findByCommentIdAndUserId(commentId, user.getId()).orElseThrow(() -> new LikeException(ErrorStatus.COMMENT_LIKE_NOT_FOUND));
    }
}