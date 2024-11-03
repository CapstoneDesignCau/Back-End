package capstone.capstone01.domain.hashtag.domain.repository;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Optional<Hashtag> findByHashtag(String hashtag);
    List<Hashtag> findByHashtagContaining(String keyword);

}
