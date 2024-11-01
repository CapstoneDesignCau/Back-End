package capstone.capstone01.domain.feedback.domain.repository;

import capstone.capstone01.domain.feedback.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
