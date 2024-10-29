package capstone.capstone01.domain.hashtag.domain.repository;

import capstone.capstone01.domain.hashtag.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findByHashtag(String hashtag);
    List<Hashtag> findByHashtagContaining(String keyword);

}
