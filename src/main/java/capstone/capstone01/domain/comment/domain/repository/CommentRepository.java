package capstone.capstone01.domain.comment.domain.repository;

import capstone.capstone01.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
