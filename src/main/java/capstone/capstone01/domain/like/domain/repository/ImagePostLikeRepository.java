package capstone.capstone01.domain.like.domain.repository;

import capstone.capstone01.domain.like.domain.ImagePostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagePostLikeRepository extends JpaRepository<ImagePostLike, Long> {
    Boolean existsByImagePostIdAndUserId(Long imagePostId, Long userId);

    Optional<ImagePostLike> findByImagePostIdAndUserId(Long imagePostId, Long userId);

    Long countByImagePostIdAndIsDeletedFalse(Long imagePostId);

}