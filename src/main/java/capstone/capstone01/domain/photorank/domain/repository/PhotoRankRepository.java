package capstone.capstone01.domain.photorank.domain.repository;

import capstone.capstone01.domain.photorank.domain.PhotoRank;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PhotoRankRepository extends JpaRepository<PhotoRank, Long> {
    List<PhotoRank> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate, PageRequest pageRequest);
}