package capstone.capstone01.domain.like.domain.repository;

import capstone.capstone01.domain.like.domain.PostLike;
import capstone.capstone01.domain.post.domain.Post;
import capstone.capstone01.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Boolean existsByPostIdAndUserId(Long postId, Long userId);

    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);

    Long countByPostIdAndIsDeletedFalse(Long postId);

    Boolean existsByPostAndUserAndIsDeletedFalse(Post post, User user);
}