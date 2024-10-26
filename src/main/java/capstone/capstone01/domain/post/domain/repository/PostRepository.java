package capstone.capstone01.domain.post.domain.repository;

import capstone.capstone01.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.isDeleted = false ORDER BY p.createdAt DESC")
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND p.createdAt >= :startDate ORDER BY p.likeCount DESC, p.createdAt DESC")
    List<Post> findTopPostsByLikeCount(@Param("startDate") LocalDateTime startDate, Pageable pageable);

}
