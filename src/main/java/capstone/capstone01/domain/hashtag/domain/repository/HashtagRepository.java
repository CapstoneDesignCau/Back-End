package capstone.capstone01.domain.hashtag.domain.repository;

import capstone.capstone01.domain.comment.domain.Comment;
import capstone.capstone01.domain.hashtag.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

}
