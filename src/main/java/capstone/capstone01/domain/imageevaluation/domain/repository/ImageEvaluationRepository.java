package capstone.capstone01.domain.imageevaluation.domain.repository;

import capstone.capstone01.domain.imageevaluation.domain.ImageEvaluation;
import capstone.capstone01.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ImageEvaluationRepository extends JpaRepository<ImageEvaluation, Long> {

    List<ImageEvaluation> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    Optional<ImageEvaluation> findByIdAndUser(Long id, User user);

    List<ImageEvaluation> findByUser(User user);

    List<ImageEvaluation> findByUserAndCreatedAtBetweenOrderByCreatedAtDesc(User user, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<ImageEvaluation> findByUser(User user, Pageable pageable);

}