package capstone.capstone01.domain.photorank.domain.repository;

import capstone.capstone01.domain.photorank.domain.PhotoRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRankRepository extends JpaRepository<PhotoRank, Long> {

}
