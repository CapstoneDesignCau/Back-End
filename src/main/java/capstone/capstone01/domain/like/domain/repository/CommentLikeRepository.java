package capstone.capstone01.domain.like.domain.repository;

import capstone.capstone01.domain.like.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Boolean existsByCommentIdAndUserId(Long commentId, Long userId);

    Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId);

    Long countByCommentIdAndIsDeletedFalse(Long commentId);

}
